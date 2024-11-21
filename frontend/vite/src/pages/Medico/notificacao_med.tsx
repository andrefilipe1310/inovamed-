
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
            <div
                className="container-card-notificacoes"
                style={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    padding: "20px",
                    maxWidth: "1200px",
                    margin: "0 auto",
                    width: "100%",
                }}
            >
                {notificacaomed.map((notificacaomed, index) => (
                    <div
                        key={index}
                        className="div-dados-notificacaomed"
                        style={{
                            backgroundColor: "#fff",
                            borderRadius: "8px",
                            boxShadow: "0 4px 10px rgba(0, 0, 0, 0.1)",
                            marginBottom: "20px",
                            padding: "20px",
                            width: "100%",
                            maxWidth: "500px",
                            transition: "transform 0.3s ease, box-shadow 0.3s ease",
                          
                        }}
                    >
                        <h2
                            style={{
                                fontSize: "1.5rem",
                                color: "#028275",
                                marginBottom: "10px",
                              
                            }}
                        >
                            {notificacaomed.titulo}
                        </h2>
                        <div
                            className="corpo-notificacaomed"
                            style={{
                                color: "#555",
                                lineHeight: "1.6",
                               
                            }}
                        >
                            <p
                                style={{
                                    fontSize: "1rem",
                                    color: "#333",
                                    margin: "5px 0",
                                    
                                   
                                }}
                            >
                                {notificacaomed.remetente}
                            </p>
                            <p
                                style={{
                                    fontSize: "1rem",
                                    color: "#333",
                                    margin: "5px 0",
                                 
                                }}
                            >
                                {notificacaomed.corpo}
                            </p>
                        </div>
                       
                    </div>
                ))};
              </div>
        </>
    );
}