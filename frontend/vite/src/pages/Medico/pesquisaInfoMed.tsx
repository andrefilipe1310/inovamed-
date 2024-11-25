import { useSearchParams } from "react-router-dom";
import Navbar from "../../components/Navbar";
import { useEffect, useState } from "react";
import api from "../../config/axiosConfig";
import { ResearchResponseDTO } from "../../types/ResearchTypes";
import { ApplicationRequestDTO } from "../../types/ApplicationTypes";

export default function PesquisaInfoMed() {
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  const [pesqInfo, setPesqInfo] = useState<any[]>([]);
  const [selectedPatientId, setSelectedPatientId] = useState<number>(-1);
  const [research, setResearch] = useState<ResearchResponseDTO | null>(null);
  const [error, setError] = useState("");
  const [application, setApplication] = useState<ApplicationRequestDTO>({
    patientCode: -1,
    researchCode: -1,
    message: "",
    statusApplication: "PENDING",
  });

  // Buscar todos os pacientes do médico
  const handleFindAllPatientsByDoctor = async () => {
    try {
      const response = await api.get("/doctor/patients");
      setPesqInfo(response.data);
    } catch (error) {
      console.error("Erro ao buscar pacientes:", error);
    }
  };

  // Buscar pesquisa pelo código
  const handleFindByCodeResearch = async () => {
    try {
      const response = await api.get(`/research/code/${id}`);
      setResearch(response.data || null);
    } catch (error) {
      setError(`Erro na busca por pesquisas: ${id}`);
      console.error(`Erro na busca por pesquisas: ${id}`, error);
    }
  };

  // Buscar histórico do paciente pelo ID
  const handleFindPatientHistory = async (patientId: number) => {
    try {
      const response = await api.get(`/patients/${patientId}/history`);
      setApplication((prev) => ({
        ...prev,
        message: response.data.history || "Histórico indisponível",
      }));
    } catch (error) {
      console.error("Erro ao buscar histórico do paciente:", error);
      setApplication((prev) => ({
        ...prev,
        message: "Erro ao carregar histórico do paciente.",
      }));
    }
  };

  // Função para baixar o PDF
  const downloadAttachment = (name: string, archiveBase64: string) => {
    const byteCharacters = atob(archiveBase64);
    const byteNumbers = Array.from(byteCharacters, (char) => char.charCodeAt(0));
    const byteArray = new Uint8Array(byteNumbers);
    const blob = new Blob([byteArray], { type: "application/pdf" });

    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = name;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  // Criar aplicação para o paciente na pesquisa
  const handleCreateApplication = () => {
    if (selectedPatientId === -1) {
      alert("Selecione um paciente antes de enviar a candidatura.");
      return;
    }

    const applicationData: ApplicationRequestDTO = {
      ...application,
      patientCode: selectedPatientId,
      researchCode: research?.code || -1,
    };

    api
      .post("/application", applicationData)
      .then(() => {
        alert("Candidatura enviada com sucesso!");
      })
      .catch((error) => {
        console.error("Erro ao enviar candidatura:", error);
        alert("Erro ao enviar candidatura.");
      });
  };

  useEffect(() => {
    if (id) {
      handleFindAllPatientsByDoctor();
      handleFindByCodeResearch();
    }
  }, [id]);

  return (
    <>
      <Navbar />
      <div className="container-page">
        {error && <p style={{ color: "red" }}>{error}</p>}
        {research ? (
          <div className="principal">
            <div
              className="container-pesq-all"
              style={{ display: "flex", flexDirection: "column", width: "100%", alignItems: "center" }}
            >
              <h1 className="title-pesquisa" style={{ textAlign: "center" }}>
                {research.title} - {research.code}
              </h1>
              <h2 className="subtitle-pesquisa">Pesquisa da área de {research.area}</h2>

              <div className="section-1">
                <p>
                  <strong>Nº de pacientes:</strong> {research.numberOfPatients}
                </p>
                <p>
                  <strong>Médico responsável:</strong> {research.responsibleDoctors.join(", ")}
                </p>
              </div>

              <p style={{ width: "80%" }}>
                <strong>Instituição responsável:</strong> {research.institutions.join(", ")}
              </p>
              <p style={{ width: "80%" }}>
                <strong>Descrição:</strong> {research.description}
              </p>

              <div className="section-2">
                <strong>Critérios de elegibilidade:</strong>
                <div>
                  <p>
                    <strong>Critério de inclusão:</strong>
                    <ul>
                      {research.criteria.inclusion.map((rc, index) => (
                        <li key={index}>{rc}</li>
                      ))}
                    </ul>
                  </p>
                  <p>
                    <strong>Critério de exclusão:</strong>
                    <ul>
                      {research.criteria.exclusion.map((ec, index) => (
                        <li key={index}>{ec}</li>
                      ))}
                    </ul>
                  </p>
                </div>
              </div>

              <div className="section-4">
                <strong>Documentos:</strong>
                <div>
                  {research.attachments && research.attachments.length > 0 ? (
                    research.attachments.map((attachment, index) => (
                      <div key={index} className="div-baixar">
                        <p>{attachment.name}</p>
                        <button onClick={() => downloadAttachment(attachment.name, attachment.archive)}>
                          Baixar
                        </button>
                      </div>
                    ))
                  ) : (
                    <div>Nenhum arquivo para baixar</div>
                  )}
                </div>
              </div>

              <select
                name="selectWho"
                onChange={(e) => {
                  const patientId = Number(e.target.value);
                  setSelectedPatientId(patientId);
                  if (patientId !== -1) {
                    handleFindPatientHistory(patientId);
                  }
                }}
              >
                <option value="-1">Selecione o paciente</option>
                {pesqInfo.map((info) => (
                  <option value={info.id} key={info.id}>
                    {info.name}
                  </option>
                ))}
              </select>
              <button onClick={handleCreateApplication}>Candidatar</button>
            </div>
          </div>
        ) : (
          <p>Carregando informações da pesquisa...</p>
        )}
      </div>
    </>
  );
}
