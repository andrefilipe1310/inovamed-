import Navbar from "../../components/Navbar";
import criarnotificacao from './criarnotificacao_med.json'
import { Link } from "react-router-dom";
export default function MedCriarNotificacao(){

    interface notificacaomed {
        id: number,
        titulo: string;
        previlcorpo: string;
    }
    const notificacaomed:notificacaomed[] = criarnotificacao
    return(
        <>
            <Navbar/>
            <div className='container-page-medico'>
                <h1 className='h1notificacao'>NOTIFICAÇÕES</h1>
                    {notificacaomed.map((notificacaomed) => (
                        <div className="notificacaomed">
                            <div className="conteiner-dados-notificacao">
                                <div className="background-dados-notificacao">
                                    <div className="titulo-notificacaomed">
                                        <h3>{notificacaomed.titulo}</h3>
                                    </div>
                                    <p className="p-previl-notificacao">{notificacaomed.previlcorpo}</p>
                                    <Link to={`/medico/notificacao_med?id=${notificacaomed.id}`} className="button-notificacao-med"><img className="imgbuttonmed" src="../../../public/seta-direita.png" alt="" /></Link>
                                </div>
                            </div>
                        </div>
                    ))}
            </div>
        </>
    )
}