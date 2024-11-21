
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
    return (
        <>
            <Navbar/>
            
            <div
        className="container-page-medico"
        style={{
          width: "100%",
          margin: "0 12%",
          padding: "1rem",
          textAlign: "center",
        }}
      >
        <h1
          className="h1notificacaopac"
          style={{
            color: "#028275",
            marginBottom: "2rem",
          }}
        >
          NOTIFICAÇÕES
        </h1>
  
       
  
        <div
          className="container-card-notificacoespac"
          style={{
            width: "70%",
            display: "flex",
            flexDirection: "column",
            gap: "1.5rem",
            alignItems: "center",
          }}
        >
          {notifications.map((notification) => (
            <div
              className="div-dados-notifcacaopac"
              key={notification.title}
              style={{
                backgroundColor: "#fffafa",
                borderRadius: "8px",
                margin: "1% 0",
                padding: "1.5rem",
                boxShadow: "0 6px 4px rgba(0, 0, 0, 0.1)",
                border: "1px solid #0282751a",
                width: "78%",
                textAlign: "left",
              }}
            >
              <h2
                style={{
                  color: "#028275",
                  fontSize: "1.5rem",
                  fontWeight: "bold",
                  fontFamily: "Georgia, serif",
                  marginBottom: "1rem",
                }}
              >
                {notification.title}
              </h2>
              <div className="corpo-notificacaomed">
                <div
                  style={{
                    display: "flex",
                    alignItems: "center",
                    gap: "0.5rem",
                    marginBottom: "0.5rem",
                  }}
                >
                  <h4
                    style={{
                      fontSize: "1.2rem",
                      margin: 0,
                    }}
                  >
                    Remetente:
                  </h4>
                  <p
                    style={{
                      fontWeight: "bold",
                      fontSize: "1.2rem",
                      margin: 0,
                    }}
                  >
                    {notification.sender}
                  </p>
                </div>
                <div>
                  <h4
                    style={{
                      fontSize: "1.2rem",
                      marginBottom: "0.2rem",
                    }}
                  >
                    Mensagem:
                  </h4>
                  <p
                    style={{
                      fontSize: "1.2rem",
                      color: "#000",
                      lineHeight: "1.5",
                      margin: 0
                    }}
                  >
                    {notification.message}
                  </p>
                </div>
  
                <div className="section-4">
                  <h3>Documentos:</h3>
                  <div
                    style={{
                      display: "flex",
                      alignItems: "center",
                      gap: "1rem",
                      marginTop: "0.5rem",
                      flexDirection: "column",
                      
                    }}
                  >
                    {(!notification || !notification.attachment?.length) && (
                      <div style={{ color: "#999" }}>
                        Nenhum arquivo para baixar
                      </div>
                    )}
                    {notification?.attachment?.map((attachment, index) => (
                      <div
                        key={index}
                        style={{
                          display: "flex",
                          flexDirection: "column",
                          alignItems: "flex-start",
                          marginBottom: "1rem",
                        }}
                      >
                        <p
                          style={{
                            marginRight: "0.5rem",
                            marginLeft: "0.5rem",
                          }}
                        >
                          {attachment.name}
                        </p>
                        <button
                          onClick={() =>
                            downloadAttachment(attachment.name, attachment.archive)
                          }
                          style={{
                            backgroundColor: "#028275",
                            color: "#fff",
                            border: "none",
                            padding: "0.5rem 1rem",
                            fontSize: "1rem",
                            borderRadius: "0.5rem",
                            cursor: "pointer",
                            transition: "background-color 0.3s",
                            marginTop: "0.5rem", 
                          }}
                        >
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
    );
}