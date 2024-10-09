
import Navbar from "../../components/Navbar";
import React, { useEffect, useState } from 'react';

export default function RepListaPesquisas(){
  const pesquisas:{titulo:string, desc:string}[] =[
    {
      titulo: "pesquisa alzimer asdhoisahdoiajsd",
      desc:"A pesquisa médica foca no desenvolvimento de uma terapia inovadora para Alzheimer, utilizando um ensaio clínico com 150 pacientes em estágios iniciais da doença. O estudo avalia a eficácia de um novo composto que visa reduzir a formação de placas amiloides no cérebro, além de monitorar alterações cognitivas e de comportamento. Resultados iniciais mostram promissora melhoria na memória e na função cerebral, com efeitos colaterais mínimos. Os pesquisadores planejam compartilhar suas descobertas em uma conferência internacional sobre neurociências."
    },
    {
      titulo: "pesquisa alzimer 2",
      desc:"A pesquisa médica investiga um novo biomarcador para o diagnóstico precoce do Alzheimer, envolvendo 300 participantes com histórico familiar da doença. O estudo busca identificar alterações nas proteínas do líquido cefalorraquidiano que possam indicar o início da neurodegeneração. Os resultados preliminares sugerem que o biomarcador pode prever com precisão a progressão da doença, possibilitando intervenções mais eficazes. Os cientistas esperam publicar suas descobertas em uma renomada revista de neurologia no próximo semestre."
    },
    {
      titulo: "pesquisa alzimer 2",
      desc:"A pesquisa médica investiga um novo biomarcador para o diagnóstico precoce do Alzheimer, envolvendo 300 participantes com histórico familiar da doença. O estudo busca identificar alterações nas proteínas do líquido cefalorraquidiano que possam indicar o início da neurodegeneração. Os resultados preliminares sugerem que o biomarcador pode prever com precisão a progressão da doença, possibilitando intervenções mais eficazes. Os cientistas esperam publicar suas descobertas em uma renomada revista de neurologia no próximo semestre."
    },
    {
      titulo: "pesquisa alzimer 2",
      desc:"A pesquisa médica investiga um novo biomarcador para o diagnóstico precoce do Alzheimer, envolvendo 300 participantes com histórico familiar da doença. O estudo busca identificar alterações nas proteínas do líquido cefalorraquidiano que possam indicar o início da neurodegeneração. Os resultados preliminares sugerem que o biomarcador pode prever com precisão a progressão da doença, possibilitando intervenções mais eficazes. Os cientistas esperam publicar suas descobertas em uma renomada revista de neurologia no próximo semestre."
    },
    {
      titulo: "pesquisa alzimer 2",
      desc:"A pesquisa médica investiga um novo biomarcador para o diagnóstico precoce do Alzheimer, envolvendo 300 participantes com histórico familiar da doença. O estudo busca identificar alterações nas proteínas do líquido cefalorraquidiano que possam indicar o início da neurodegeneração. Os resultados preliminares sugerem que o biomarcador pode prever com precisão a progressão da doença, possibilitando intervenções mais eficazes. Os cientistas esperam publicar suas descobertas em uma renomada revista de neurologia no próximo semestre."
    },
    {
      titulo: "pesquisa alzimer 2",
      desc:"A pesquisa médica investiga um novo biomarcador para o diagnóstico precoce do Alzheimer, envolvendo 300 participantes com histórico familiar da doença. O estudo busca identificar alterações nas proteínas do líquido cefalorraquidiano que possam indicar o início da neurodegeneração. Os resultados preliminares sugerem que o biomarcador pode prever com precisão a progressão da doença, possibilitando intervenções mais eficazes. Os cientistas esperam publicar suas descobertas em uma renomada revista de neurologia no próximo semestre."
    },
    {
      titulo: "pesquisa alzimer 2",
      desc:"A pesquisa médica investiga um novo biomarcador para o diagnóstico precoce do Alzheimer, envolvendo 300 participantes com histórico familiar da doença. O estudo busca identificar alterações nas proteínas do líquido cefalorraquidiano que possam indicar o início da neurodegeneração. Os resultados preliminares sugerem que o biomarcador pode prever com precisão a progressão da doença, possibilitando intervenções mais eficazes. Os cientistas esperam publicar suas descobertas em uma renomada revista de neurologia no próximo semestre."
    }
  ]



  

    return(
        <>
        <Navbar/>
        <div className='container-page'> 
            <div className='card-border'>
                <div className="container-card">
                <h1>MINHAS PESQUISAS</h1>
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
        <button className="button-nova-pesquisa">NOVA PESQUISA +</button>
        </div>
        </>
    )
}