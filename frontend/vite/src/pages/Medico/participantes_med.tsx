import { Link } from "react-router-dom";
import Navbar from "../../components/Navbar";
import dadospart from './participantesinfo_med.json';
import { useEffect, useState } from "react";
import api from "../../config/axiosConfig";
import { error } from "console";
import { MedicalHistoryRequestDTO, PatientResponse } from "../../types/PatientTypes";
export default function MedParticipantes(){
    interface ParticipanteMed {
        id: number;
        nome: string;
        idade: number;
        genero: string;
      }
    
      const reduceTitle = (title:string):string =>{
        if (title.length > 20) {
            return title.substring(0,20)+"..."
        }
        return title
    }

      const [patientFeature,setPatientFeatures] = useState<PatientResponse[]>([])

      const handleFindPatientsByDoctorId = async() =>{
        api.get("/doctor/patients")
        .then(response =>{
            
            setPatientFeatures(response.data)
            console.log(response.data)
            console.log(patientFeature)
            
        })
        .catch(error =>{
            console.error(error)
        })
      }

      

      useEffect(()=>{
        handleFindPatientsByDoctorId()
      },[])

      const participanteMed:ParticipanteMed[] = dadospart
    return(
        <>
         <Navbar/>
         <div className='container-page-medico-part'>
            <div className="funcoes-medico-part">
                <h1 className='h1notificacao' style={{textAlign:"center"}}>Pacientes Cadastrados</h1>
                <div className="container-card-part-med">
                    <div className="container-card-part">
                        {patientFeature.map((partMed) => (
                        <div className="div-dados-part-med" key={partMed.code}>
                            <div className="div-background-part">
                                <div className="dados-card-part-med">
                                    <div className="p-participantes">
                                        <p className="p-esquerdo">Nome:</p>
                                        <p className="p-direito">{partMed.name}</p>
                                    </div>
                                    <div className="p-participantes">
                                        <p className="p-esquerdo">Gênero:</p>
                                        <p className="p-direito">{partMed.gender}</p>
                                    </div>
                                    <div className="div-link-part" style={{display:'flex',justifyContent:'center'}}>
                                        <Link to={`/medico/participantesconfiguracao?id=${partMed.code}`}><button className="button-link-part" ><img src="../config-icon.png" className="img-link-part"/></button></Link>
                                        
                                    </div>                         
                                </div>
                                <div className='linha-card-part-med'>
                                    <p className="p2-card-medico">#{reduceTitle(partMed.code)}</p>
                                </div>
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