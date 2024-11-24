import Navbar from "../../components/Navbar";
import criarnotificacao from './criarnotificacao_med.json'

export default function MedCriarNotificacao(){

    interface notificacaomed {
        id: number,
        titulo: string;
        corpo: string;
        remetente: string;
    }
    const notificacaomed:notificacaomed[] = criarnotificacao


    return(
        <>
            <Navbar/>
            <div className='container-page-medico'>
                <h1 className='h1notificacao'>NOTIFICAÇÕES</h1>
                    {notificacaomed.map((notificacaomed) => (
                        <div className="card-border">
                            <div className="container-dados-notificacao">
                                <div className="background-dados-notificacao">
                                    <div className="titulo-notificacaomed">
                                        <h3>{notificacaomed.titulo}</h3>
                                    </div>
                                    <div className="corpo-notificacao-med">
                                    <p>{notificacaomed.remetente}</p>
                                    <p>{notificacaomed.corpo}</p>
                                    </div>
                                </div>
                              
                            </div>
                        </div>
                    ))}
            </div>
        </>
    )
}