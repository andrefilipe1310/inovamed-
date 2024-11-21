
import { useEffect, useState } from 'react';
import criarnotificacao from './criarnotificacao_med.json'
import api from '../../config/axiosConfig';
import { NotificationResponseDTO } from '../../types/NotificationType';
import Navbar from '../../components/Navbar';

export default function MedNotificacao(){

    const [notifications, setNotifications] = useState<NotificationResponseDTO[]>(
        []
      );
    
      const handleFindAllNotification = async () => {
        api
          .get("/notification/doctor", {})
          .then((response) => {
          
            setNotifications(response.data)
          })
          .catch((error) => {
            console.error(error);
          });
      };
    
       // Função para baixar o PDF
       const downloadAttachment = (name:string, archiveBase64:Base64URLString) => {
        // Converte a string Base64 para um Blob
        const byteCharacters = atob(archiveBase64);
        const byteNumbers = Array.from(byteCharacters, char => char.charCodeAt(0));
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: 'application/pdf' });
    
        // Cria um link temporário para baixar o arquivo
        const link = document.createElement('a');
        link.href = URL.createObjectURL(blob);
        link.download = name;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      };
    
      useEffect(() => {
        handleFindAllNotification();
      }, []);
    interface notificacaomed {
        titulo: string;
        remetente: string;
        corpo: string;
    }
    const notificacaomed:notificacaomed[] = criarnotificacao
    return(
        <>
      <Navbar />
      <div className="container-page-medico">
        <h1 className="h1notificacaopac" style={{ color: "#028275" }}>
          NOTIFICAÇÕES
        </h1>

        

        <div className="container-card-notificacoespac">
          {notifications.map((notification) => (
            <div
              className="div-dados-notifcacaopac"
              key={notification.title}
              style={{
                backgroundColor: "white",
                borderRadius: "8px",
                padding: "16px",
                marginBottom: "16px",
                boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
                border: "none",
                minWidth:"55em"
              }}
            >
              <h2>{notification.title}</h2>
              <div className="corpo-notificacaomed">
                <p style={{ fontWeight: "bold" }}>{notification.sender}</p>
                <p>{notification.message}</p>
                <div className="section-4">
                        <strong>Documentos:</strong>
                        <div>
                            {(notification == null || notification == undefined) && <div>Nenhum arquivo para baixar</div> } 
                            {notification?.attachment.map((attachment, index) => (
                                 <div key={index}>
                                 <p>{attachment.name}</p>
                                 <button onClick={() => downloadAttachment(attachment.name, attachment.archive)}>
                                   Baixar
                                 </button>
                                 </div>
                            ))}
                        </div>
                       
                    </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
    )
    
}