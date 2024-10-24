import Navbar from "../../components/Navbar";
import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import pesquisamedico from "../../components/medicoinfo";
import pesquisas from "../../components/userInfo";
import { Pesquisa } from "../../components/userInfo";
export default function MedListPacientesPesquisas(){

    const [showPesqId, setShowpesqId] = useState<number>()
    const [showPesq,setShowPesq] = useState<Pesquisa>()
 
    useEffect(()=>{
       setShowPesq(pesquisas.find((info)=> info.id === showPesqId))
    },[showPesqId])
    return(
        <>
        <Navbar/>
        <div className='container-page'> 
            <div className='card-border'>
                <div className="container-card">
                <h1 className="h1pesqmedico">PESQUISAS INSCRITAS</h1>
                    <div className='div1'>
                    </div>
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
                <div className="container-card">
                    <h1 className="h1detalhepesq">PESQUISA 1</h1>
                            <div className='div-paragrafo'>
                                {pesquisamedico.map((info)=>(
                                <div className="conteinerdetalhepesq"> 
                                    <div className="divparagrafo">
                                        <p className='cabecalhopesquisa'>{info.pacientenum}</p>
                                        <p className='cabecalhopesquisa'>{info.dataini}</p>
                                        <p className='cabecalhopesquisa'>{info.datafim}</p>
                                    </div>
                                    <div>
                                        <p className="descpesq"><strong>Descrição: </strong>{info.desc}</p>
                                        <h2 className="h2detalhepesq">FASES</h2>
                                        <h3 className="h3detalhepesq">Fase 1</h3>
                                        <p className="fasepesq">{info.fase}</p>
                                    </div>
                                </div>
                        ))}
                        <a className='vermais' href="#">ver mais</a>
                            </div>
                </div>
            </div>
        </div>
        </>
    )
}