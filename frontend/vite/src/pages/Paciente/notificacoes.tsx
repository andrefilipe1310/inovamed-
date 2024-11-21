import { useEffect, useState } from "react";
import Navbar from "../../components/Navbar";
import { NotificationResponseDTO } from "../../types/NotificationType";
import api from "../../config/axiosConfig";

export default function PacCriarNotificacao() {
  const [notifications, setNotifications] = useState<NotificationResponseDTO[]>(
    []
  );

  const handleFindAllNotification = async () => {
    api
      .get("/notification/patient", {})
      .then((response) => {
        setNotifications(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  // Função para baixar o PDF
  const downloadAttachment = (name: string, archiveBase64: Base64URLString) => {
    // Converte a string Base64 para um Blob
    const byteCharacters = atob(archiveBase64);
    const byteNumbers = Array.from(byteCharacters, (char) =>
      char.charCodeAt(0)
    );
    const byteArray = new Uint8Array(byteNumbers);
    const blob = new Blob([byteArray], { type: "application/pdf" });

    // Cria um link temporário para baixar o arquivo
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = name;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  useEffect(() => {
    handleFindAllNotification();
  }, []);
  return (
    <>
      <Navbar />
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
          className="notificacaopac"
          style={{
            backgroundColor: "#f2f7f5",
            borderRadius: "8px",
            padding: "1.5rem",
            marginBottom: "2rem",
            textAlign: "center",
          }}
        >
          <h3
            style={{
              fontSize: "1.5rem",
              fontWeight: "bold",
              marginBottom: "1rem",
            }}
          >
            Pesquisa Clínica:
          </h3>
          <p
            style={{
              fontSize: "1.2rem",
              color: "#333",
            }}
          >
            Essa pesquisa visa mostrar o resultado do tratamento X para
            pacientes com Alzheimer.
          </p>
        </div>
  
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
  
      <style>
        {`
          @media (max-width: 800px) {
            .notificacaopac {
              text-align: center;
            }
            .notificacaopac h3 {
              margin-bottom: 0.5rem;
            }
            .notificacaopac p {
              margin-top: 0.5rem;
            }
            .container-page-medico {
              padding: 0.5rem;
            }
            .container-card-notificacoespac {
              gap: 1rem;
              width: 100%;
            }
            .div-dados-notifcacaopac {
              width: 100%;
            }
            .section-4 {
              display: flex;
              flex-direction: column;
              gap: 1rem;
            }
            button {
              width: 100%;
              padding: 0.5rem;
            }
          }
        `}
      </style>
    </>
  );
  
}
