
import Navbar from "../../components/Navbar";
import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import pesquisas from "../../components/userInfo";
import { Pesquisa } from "../../components/userInfo";

export default function RepListaPesquisas(){


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
                <h1>MINHAS PESQUISAS</h1>
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
        <Link to='/representante/novapesquisa' ><button className="button-nova-pesquisa">NOVA PESQUISA +</button></Link>
        {showPesqId ? 
            <div className='card-border' style={{marginBottom:'3vw'}}>
              <div className="container-card">
                  <h1>{showPesq?.titulo}</h1>
                  <div className="full-detail-pesq">
                    <div className="sector-1">
                        <p>{showPesq?.dataini}</p>
                        <p>{showPesq?.datafim}</p>
                    </div>
                    <div className="sector-2">
                        <p><strong>Descrição:</strong> {showPesq?.desc}</p>
                    </div>
                  </div>
                  <a href="#">ver mais</a>
                  <div className="buttons-pesq">
                        <button><img src="../../../public/config-icon.png" alt="" /></button>
                        <button><img src="../../../public/users-icon.png" alt="" /></button>
                        <button><img src="../../../public/pencil-icon.png" alt="" /></button>
                </div>
              </div>
            </div> 
          : <></>}
        </div>
        </>
    )
}