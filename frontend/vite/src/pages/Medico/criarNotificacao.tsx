import Navbar from "../../components/Navbar";
import notificacaomedico from "../../components/notificacoes-med";
import { useEffect, useState } from "react";
import { Pesquisa } from "../../components/userInfo";
export default function MedCriarNotificacao(){

    const [showPesqId, setShowpesqId] = useState<number>()
    const [showPesq,setShowPesq] = useState<Pesquisa>()
    
 
    useEffect(()=>{
      // setShowPesq(pesquisas.find((info)=> info.id === showPesqId))
    },[showPesqId])
    return(
        <>
        <Navbar/>
        <div className='container-page-medico'>
            <h1 className='h1notificacao'>NOTIFICAÇÕES</h1>
            <div className="notificacaomed">
            {notificacaomedico.map((notificacaomed) => (
                    <div className="dados-notificacaomed">
                        <div className="titulo-notificacaomed">
                            <h3>{notificacaomed.titulo}</h3>
                        </div>
                    <p>exemploexemploexemploexemploexemploexemploexemploexemploexemploexemploexemploexemplo</p>
                    </div>
                ))}
                </div>
                <div className="container-card-notificacoes">
                {notificacaomedico.map((notificacaomed) => (
                    <div className="div-dados-notificacaomed">
                    <h2>{notificacaomed.titulo}</h2>
                    <div className="corpo-notificacaomed">
                    <p>{notificacaomed.remetente}</p>
                    <p>{notificacaomed.corpo}</p>
                    </div>
                    </div>
                ))}
                </div>
        </div>
        </>
    )
}