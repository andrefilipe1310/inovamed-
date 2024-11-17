import Navbar from "../../components/Navbar";
import criarnotificacao from './criarnotificacao_med.json'

export default function MedNotificacao(){
    interface notificacaomed {
        titulo: string;
        remetente: string;
        corpo: string;
    }
    const notificacaomed:notificacaomed[] = criarnotificacao
    return(
        <>
            <div className="container-card-notificacoes">
                {notificacaomed.map((notificacaomed) => (
                    <div className="div-dados-notificacaomed">
                        <h2>{notificacaomed.titulo}</h2>
                        <div className="corpo-notificacaomed">
                            <p>{notificacaomed.remetente}</p>
                            <p>{notificacaomed.corpo}</p>
                        </div>
                    </div>
                ))}
            </div>
        </>
    )
    
}