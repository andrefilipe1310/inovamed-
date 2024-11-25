import Navbar from "../../components/Navbar";
import { useSearchParams, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../../config/axiosConfig";
import { MedicalHistoryRequestDTO, PatientResponse } from "../../types/PatientTypes";

export default function MedParticipantesConfig() {
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id"); // Obter ID do paciente

  const [patient, setPatient] = useState<PatientResponse | null>(null); // Estado para dados do paciente
  const [medicalHistory, setMedicalHistory] = useState<MedicalHistoryRequestDTO>({
    patientCode: "", // Código inicial vazio
    message: "", // Mensagem inicial vazia
  });

  // Função para buscar paciente pelo código e carregar o histórico
  const handleFindPatientByCode = async () => {
    if (!id) {
      console.error("ID do paciente não encontrado!");
      return;
    }

    try {
      const response = await api.get<PatientResponse>(`/patient/code/${id}`);
      setPatient(response.data); // Salvar dados no estado
      console.log("Paciente encontrado:", response.data);

      // Atualizar o estado do histórico médico com os dados existentes do paciente
      setMedicalHistory({
        patientCode: response.data.code, // Usar o código do paciente
        message: response.data.medicalHistory || "", // Carregar histórico, se disponível
      });
    } catch (error) {
      console.error("Erro ao buscar paciente:", error);
    }
  };

  // Função para salvar o histórico médico (editar ou criar)
  const handleCreateMedicalHistory = async () => {
    if (!medicalHistory.patientCode || !medicalHistory.message.trim()) {
      console.error("Mensagem do histórico ou código do paciente inválidos!");
      return;
    }

    try {
      const response = await api.post("/medical-history", medicalHistory);
      console.log("Histórico médico salvo com sucesso:", response.data);
    } catch (error) {
      console.error("Erro ao salvar o histórico médico:", error);
    }
  };

  // Atualizar o estado do histórico médico conforme o usuário digita
  const handleMedicalHistoryChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setMedicalHistory((prevState) => ({
      ...prevState,
      message: e.target.value,
    }));
  };

  // Executar busca quando o componente for montado
  useEffect(() => {
    handleFindPatientByCode();
  }, []);

  return (
    <>
      <Navbar />
      <div className="container-page">
        <h1 className="title-page">Paciente</h1>
        {patient ? (
          <div className="card-border">
            <div className="container-notfic-all">
              <label className="nome-part-med">Nome</label>
              <input
                className="input-nome"
                value={patient.name ?? "Nome não encontrado"}
                disabled
              />
              <label className="email-part-med">Email</label>
              <div className="div-email-genero">
                <input
                  className="input-email"
                  value={patient.email ?? "Email não encontrado"}
                  disabled
                />
                <input
                  className="input-email"
                  value={patient.gender ?? "Gênero não encontrado"}
                  disabled
                />
              </div>
              <label htmlFor="titulo">Histórico Médico</label>
              <textarea
                className="textarea-historico"
                value={medicalHistory.message} // Usa o estado para manter o valor
                onChange={handleMedicalHistoryChange} // Atualiza o estado ao digitar
                placeholder="Digite o histórico médico"
              ></textarea>
            </div>
          </div>
        ) : (
          <p>Carregando informações do paciente...</p>
        )}
        <Link to={`/medico/participantes`}>
          <button onClick={handleCreateMedicalHistory} className="button-send-notification">
            Salvar
          </button>
        </Link>
      </div>
    </>
  );
}
