import Navbar from "../../components/Navbar";
import { useSearchParams, Link } from "react-router-dom";
import pesq from './pesquisaTest.json'
import { ResearchResponseDTO } from "../../types/ResearchTypes";
import api from "../../config/axiosConfig";
import { useState, useEffect } from "react";
export default function RepPesquisaInfo(){

    const [seachParams] = useSearchParams()
    const id = seachParams.get('id')
    let [research,setResearch] = useState<ResearchResponseDTO>()
    //const pesqInfo = pesq.find((p)=> p.id.toString() === id)
    const handleFindByCodeResearch = async ()=>{
        api.get(`/research/code/${id}`)
        .then(response =>{
            
            if (!response.data) {
             return null   
            }
            setResearch(response.data)
            console.log(research)
           
        })
        .catch(error =>{
            console.error(`Erro na busca por pesquisas: ${id}`,error)
        })
        
      
    }
   
    useEffect(() => {
        if (id) {
            handleFindByCodeResearch();
        }
    }, [id]);
    return(

        <>
        <Navbar/>
        <div className="container-page" >

            <h1 className="title-pesquisa">{research?.title} - {research?.code}</h1>
            <h2 className="subtitle-pesquisa">Pesquisa da área de {research?.area}</h2>
            <div style={{display:"flex", width:"20vw", justifyContent:"space-evenly"}}>
                <div className="card-border">
                    <div className="container-button">
                        <Link to={`/representante/alterarpesquisa?id=${research?.code}`} className="button-to-edit"><img src="../../../public/pencil-icon.png" alt="" /></Link>
                    </div>
                </div>
                <div className="card-border">
                    <div className="container-button">
                        <Link to={`/representante/infopaciente?id=${research?.code}`} className="button-to-edit"><img src="../../../public/users-icon.png" alt="" /></Link>
                    </div>
                </div>
            </div>
            <div className="section-1">
                <p><strong>Nº de pacientes:</strong> {research?.numberOfPatients}</p>
                <p><strong>Médico resposnável:</strong> {research?.responsibleDoctors.map((doc)=>(doc+', '))}</p>
            </div>
            <p style={{width:"80%"}}><strong>Instituição responsável:</strong> {research?.institutions.map((inst,index)=>(<p style={{display:"inline"}}>{inst}{index === research?.institutions.length - 1 ? '' : ', '}</p>))}</p>
            <p style={{width:"80%"}}><strong>Descrição:</strong> {research?.description}</p>
            <div className="section-2">
                <strong>Critérios de elegibilidade:</strong>
                <div>
                   {/* <p> <strong>Idade: </strong>{research?.criteria.ageRange }</p> */}

                    <p><strong>Critério de inclusão:</strong> {research?.criteria.inclusion.map((rc)=>(
                        <li>{rc}</li>
                    ))}
                    </p>
                    <p><strong>Critério de exclusão:</strong> {research?.criteria.exclusion.map((ec)=>(
                        <li>{ec}</li>
                    ))}
                    </p>
                </div>
            </div>
            <p style={{width:"80%"}}><strong>Data de inicio e fim:</strong> {research?.studyDuration.start} à {research?.studyDuration.end}</p>
            <div className="section-3">
                <strong>Fases da pesquisa:</strong>
                {research?.phases.map((fase) => <li><strong>Fase {fase.number}:</strong> {fase.description}</li>)}
            </div>
            <p style={{width:"80%"}}><strong>Fase atual: </strong> Fase {research?.currentPhase}</p>
            <p style={{width:"80%"}}><strong>Localização: </strong>{research?.location}</p>
            <div className="section-4">
                <strong>Documentos:</strong>
                <div>
                    {research?.attachments.map((doc)=><button>{doc.name}</button>)}
                </div>
            </div>
        </div>
        
        </>
    )
}