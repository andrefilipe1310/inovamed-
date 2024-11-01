import { useState } from "react";
import Navbar from "../../components/Navbar";

export default function RepNovaPesquisa(){

    interface Study {
        id: number;
        code: number;
        title: string;
        area: string;
        numberOfPatients: number;
        availableVacancies: number;
        responsibleDoctors: string[];
        institutions: string[];
        description: string;
        criteria: Criteria;
        studyDuration: StudyDuration;
        phases: Phase[];
        currentPhase: number;
        location: string;
        attachments: Attachment[];
        patients: Patient[];
        clinicalRepresentative: ClinicalRepresentative;
      }
      
      interface Criteria {
        ageRange: string;
        requiredCondition: string;
        exclusionCriteria: string;
      }
      
      interface StudyDuration {
        startDate: string; // consider using Date if necessary
        endDate: string;   // consider using Date if necessary
      }
      
      interface Phase {
        phaseNumber: number;
        description: string;
      }
      
      interface Attachment {
        id: number;
        fileName: string;
        fileType: string;
      }
      
      interface Patient {
        id: number;
        name: string;
        email: string;
      }
      
      interface ClinicalRepresentative {
        id: number;
        name: string;
        email: string;
      }

      const [qtMed,setqtMed] = useState([<input type='text' name='respMed' /> ])
      const [qtRespInst,setQtRespInst] = useState([<input type='text' name='respInst' /> ])
      const [qtIncludeCriteria,setQtIncludeCriteria] = useState([<input type='text' name='includeCriteria' /> ])
      const [qtExcludeCriteria,setQtExcludeCriteria] = useState([<input type='text' name='excludeCriteria' /> ])
      const [fases,setFases] = useState([<><label htmlFor="fases">Fase 1</label><input type='text' name='fases' /></> ])


    return(
        <>
       <Navbar/>
            <div className='container-page'> 
                <h1 className="title-page">NOVA PESQUISA</h1>
                <div className="card-border" style={{marginBottom:"2vw"}}>
                    <div className='form-new-research'>
                        <div className='session'>
                            <label htmlFor="title">Título</label>
                            <input type="text" name='title' /> 
                        </div>

                        <div className='session'>
                            <div>
                                <label htmlFor="pacients">Pacientes</label>
                                <input type='number' name='pacients' /> 
                            </div>
                            <div>
                                <label htmlFor="area">Área do estudo</label>
                                <input type="text" name='area' /> 
                            </div>
                        </div>

                        <div className='session'>
                            <label htmlFor="respMed">Médico responsável</label>
                            {qtMed}
                            <button onClick={()=>setqtMed([...qtMed,<><input type='text' name='respMed' /><button onClick={()=>setqtMed(qtMed.slice(0, qtMed.length))}>-</button></>])}>+</button>
                        </div>

                        <div className='session'>
                            <label htmlFor="respInst">Instituição responsável</label>
                            {qtRespInst}
                            <button onClick={()=>setQtRespInst([...qtRespInst,<><input type='text' name='respInst' /><button onClick={()=>setQtRespInst(qtRespInst.slice(0, qtRespInst.length))}>-</button></>])}>+</button>
                        </div>

                        <div className='session'>
                            <label htmlFor="includeCriteria">Critérios de Inclusão: </label>
                            {qtIncludeCriteria}
                            <button onClick={()=>setQtIncludeCriteria([...qtIncludeCriteria,<><input type='text' name='respInst' /><button onClick={()=>setQtIncludeCriteria(qtIncludeCriteria.slice(0, qtIncludeCriteria.length))}>-</button></>])}>+</button>
                            <label htmlFor="excludeCriteria">Critérios de Exclusão: </label>
                            {qtExcludeCriteria}
                            <button onClick={()=>setQtExcludeCriteria([...qtExcludeCriteria,<><input type='text' name='respInst' /><button onClick={()=>setQtExcludeCriteria(qtExcludeCriteria.slice(0, qtExcludeCriteria.length))}>-</button></>])}>+</button>
                        </div>

                        <div className='session'>
                            <label htmlFor="desc">Descrição da pesquisa</label>
                            <textarea name='desc' /> 
                        </div>

                        <div className='session'>
                            <div>
                                <label htmlFor="begginDate">Data de inicio</label>
                                <input type='date' name='begginDate' />
                            </div>
                            <div>
                                <label htmlFor="endDate">Data de encerramento</label>
                                <input type='date' name='endDate' />
                            </div>
                        </div>
                        
                        <div className='session'>
                            <p><strong>Fases</strong></p>
                            {fases}
                            <button onClick={()=>setFases([...fases,<><label>Fase {fases.length+1}</label><input type='text' name='fases' /><button onClick={()=>setFases(fases.slice(0,fases.length))}>-</button></>])}>+</button>
                        </div>

                        <div className='session'>
                            <label htmlFor="local"> Local da pesquisa (Apenas estado e município)</label>
                            <input type='text' name='local' /> 
                        </div>

                        <div className='session'>
                            <label htmlFor="documents"> Documentos (PDF): </label>
                            <div></div>
                            <button>+</button>
                        </div>
                    
                        <button type="submit" className="send-data">CRIAR PESQUISA</button>

                    </div>
            </div>
            </div>
        </>
    )
}