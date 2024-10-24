import Navbar from "../../components/Navbar";
import React, { useEffect, useState } from 'react';
import notificacaomedico from "../../components/notificacaoinfo";
import pesquisas from "../../components/userInfo";
import { Pesquisa } from "../../components/userInfo";
export default function MedCriarNotificacao(){

    const [showPesqId, setShowpesqId] = useState<number>()
    const [showPesq,setShowPesq] = useState<Pesquisa>()
 
    useEffect(()=>{
       setShowPesq(pesquisas.find((info)=> info.id === showPesqId))
    },[showPesqId])
    return(
        <>
        <Navbar/>
        <div className='container-page'> 
        <h1 className='h1notificacao'>Notificações</h1>
            <div className='card-border-notificacao-med'>
                <div className="container-card">
                <div className='card-pesquisas' >
                        {pesquisas.map((info)=>(
                            <div className='pesq-detail' onClick={()=>setShowpesqId(info.id)}> 
                                <p className='title-pesq'>{info.titulo}</p>
                                <p className='desc-pesq'>{info.desc}</p>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
            <div className='card-border-pesq'>
            <div className="container-card" >
            <div>
                                {notificacaomedico.map((info)=>(
                                <div className="container-notificacao-med"> 
                                    <h2 className="h2-notificacao">{info.titulo}</h2>
                                    <div className="div-corpo-notificacao">
                                        <p className="remetente">{info.remetente}</p>
                                        <p>{info.corpo}</p>
                                    </div>
                                    <div className='link-anexo'>
                                        <a href='#' className='link-notificacao'>Link</a>
                                        <img></img>
                                        <input type="file" className='anexo'></input>
                                    </div>
                                </div>
                        ))}
        </div>
        </div>
        </div>
        </div>
        </>
    )
}