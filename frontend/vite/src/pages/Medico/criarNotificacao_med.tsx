import { useEffect, useState } from "react";
import Navbar from "../../components/Navbar";
import api from "../../config/axiosConfig";
import { NotificationResponseDTO } from "../../types/NotificationType";
import criarnotificacao from './criarnotificacao_med.json'

export default function MedCriarNotificacao(){

    interface notificacaomed {
        id: number,
        titulo: string;
        corpo: string;
        remetente: string;
    }
    const notificacaomed:notificacaomed[] = criarnotificacao
    const [notifications, setNotifications] = useState<NotificationResponseDTO[]>(
        []
      );
    
      const handleFindAllNotification = async () => {
        api
          .get("/notification/doctor", {})
          .then((response) => {
            console.log(response.data)
            setNotifications(response.data)
          })
          .catch((error) => {
            console.error(error);
          });
      };
      useEffect(() => {
        handleFindAllNotification();
      }, []);

    return(
        <>
            <Navbar/>
            <div className='container-page-medico'>
                <h1 className='h1notificacao'>NOTIFICAÇÕES</h1>
                    {notifications.map((notificacaomed) => (
                        <div className="card-border">
                            <div className="container-dados-notificacao">
                                <div className="background-dados-notificacao">
                                    <div className="titulo-notificacaomed">
                                        <h3>{notificacaomed.title}</h3>
                                    </div>
                                    <div className="corpo-notificacao-med">
                                    <p>{notificacaomed.sender}</p>
                                    <p>{notificacaomed.message}</p>
                                    </div>
                                </div>
                              
                            </div>
                        </div>
                    ))}
            </div>
        </>
    )
}