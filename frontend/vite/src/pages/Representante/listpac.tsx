import { useEffect, useState } from "react";
import Navbar from "../../components/Navbar";
import { useSearchParams } from "react-router-dom";
import api from "../../config/axiosConfig";
import { NotificationPatientResponseDTO } from "../../types/NotificationType";

// Definindo os tipos para as interfaces utilizadas no código
interface StudieInfo {
  title: string;
  code: number;
}

interface MedicalHistory {
  id: number;
  conditions: string[];
  medications: string[];
}

interface Pacient {
  code: number;
  name: string;
  gender: string;
  birth: string;
  digitalSignatureConsent: boolean;
  responsibleDoctor: boolean;
  medicalHistory: MedicalHistory;
}

export default function ListaPac() {
  const [requestApplicationByResearchCode, setRequestApplicationByResearchCode] = useState<{ researchCode: number }>({
    researchCode: 0, // Iniciar com 0 para evitar erro
  });
  const [notificationPatientResponse, setNotificationPatientResponse] = useState<NotificationPatientResponseDTO[]>([]);
  const [searchParams] = useSearchParams();
  const id = searchParams.get('id');

  const [selectedParticipantToAprove, setSelectedParticipantToAprove] = useState<Pacient | null>(null);
  const [selectedParticipantAproved, setSelectedParticipantAproved] = useState<Pacient | null>(null);
  const [toAproveReproval, setToAproveReproval] = useState<boolean>(false);
  const [aprovedReproval, setAprovedReproval] = useState<boolean>(false);

  const infoPesq: StudieInfo = {
    title: "Pesquisa Teste", // Aqui deve ser o título real da pesquisa
    code: 123, // Código real da pesquisa
  };

  // Função para buscar as aplicações de pesquisa
  const handleFindApplicationsByResearchCode = async () => {
    if (id) {
      setRequestApplicationByResearchCode({ researchCode: Number(id) });
      try {
        console.log(id)
        
        const response = await api.get(`/application/research/${id}`);
        setNotificationPatientResponse(response.data);
        console.log(response.data)
      } catch (error) {
        console.error("Erro ao buscar aplicações:", error);
      }
    }
  };

  // Chama a função quando o componente é montado
  useEffect(() => {
    handleFindApplicationsByResearchCode();
  }, [id]);

  return (
    <>
      <Navbar />
      <div className="container-page">
        <h1 className="title-page">PARTICIPANTES</h1>
        <h2 className="subtitle-pac">
          {infoPesq.title} - #{infoPesq.code}
        </h2>

        <div className="section-pac">
          <h3>SOLICITAÇÕES</h3>
          <div className="participant-list">
            {notificationPatientResponse.map((pac) => (
              <button
                key={pac.code}
                onClick={() => {
                  setSelectedParticipantToAprove(pac);
                  setToAproveReproval(false);
                }}
              >
                {pac.name}
              </button>
            ))}
          </div>
        </div>

        {selectedParticipantToAprove && (
          <div className="edit-patient">
            <h2>{selectedParticipantToAprove?.name}</h2>
            <p><strong>Código: </strong>{selectedParticipantToAprove?.code}</p>
            <p><strong>Gênero: </strong>{selectedParticipantToAprove?.gender}</p>
            <div>
              <button>✔️</button>
              <button onClick={() => setToAproveReproval(true)}>❌</button>
            </div>
          </div>
        )}

        {toAproveReproval && (
          <div className="reproval-motive">
            <label htmlFor="motive">Por qual motivo o paciente não foi aceito na pesquisa?</label>
            <textarea name="motive" id=""></textarea>
            <button>FINALIZAR</button>
            <button onClick={() => setToAproveReproval(false)}>x</button>
          </div>
        )}

        <div className="section-pac">
          <h3>APROVADOS</h3>
          <div className="participant-list">
            {notificationPatientResponse.map((pac) => (
              <button
                key={pac.code}
                onClick={() => setSelectedParticipantAproved(pac)}
              >
                {pac.name}
              </button>
            ))}
          </div>
        </div>

        {selectedParticipantAproved && (
          <div className="edit-patient">
            <h2>{selectedParticipantAproved?.name}</h2>
            <p><strong>Código: </strong>{selectedParticipantAproved?.code}</p>
            <p><strong>Gênero: </strong>{selectedParticipantAproved?.gender}</p>
            <div>
              <button onClick={() => setAprovedReproval(true)}>❌</button>
            </div>
          </div>
        )}

        {aprovedReproval && (
          <div className="reproval-motive">
            <label htmlFor="motive">Por que o paciente está sendo desligado da pesquisa?</label>
            <textarea name="motive" id=""></textarea>
            <button>FINALIZAR</button>
            <button onClick={() => setAprovedReproval(false)}>x</button>
          </div>
        )}
      </div>
    </>
  );
}
