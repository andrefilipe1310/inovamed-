
import Navbar from "../../components/Navbar";
import React, { useEffect, useState } from 'react';
 
 
 
export default function PacListaPesquisas(){
  const pesquisas:{titulo:string, desc:string}[] =[
    {
      titulo: "Pesquisa Clinica: Alzimer ",
      desc:" Eficácia e Segurança do Medicamento X no Tratamento de Pacientes com Alzheimer"
    },
    {
      titulo: "Pesquisa Clinica: Alzimer",
      desc:" Eficácia e Segurança do Medicamento X no Tratamento de Pacientes com Alzheimer"
    },
   
 
  ]  
 
    return(
        <>
        <Navbar/>
        <div className='container-page'>
            <div className='card-border'>
                <div className="container-card">
                <h1>PESQUISAS INSCRITAS</h1>
                    <div className='card-pesquisas' >
                        {pesquisas.map((info)=>(
                            <div className='pesq-detail' >
                                <p className='title-pesq'>{info.titulo}</p>
                                <p className='desc-pesq'>{info.desc}</p>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
        </>
    )
}