import { useState } from 'react';
import Navbar from '../../components/Navbar';
import ResearchData from './sigleStudieTest.json'

export default function RepAlterarPesquisa(){
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
            requiredCondition: string[];
            exclusionCriteria: string[];
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
          
         const ResData:Study = ResearchData

         const [qtMed,setqtMed] = useState(ResData.responsibleDoctors.map((med)=>(<input type='text' name='respMed' value={med} />)))
         const [qtRespInst,setQtRespInst] = useState(ResData.institutions.map((res)=>(<input type='text' name='respInst' value={res} />)))
         const [qtIncludeCriteria,setQtIncludeCriteria] = useState(ResData.criteria.requiredCondition.map((crit)=><input type='text' name='includeCriteria' value={crit} />))
         const [qtExcludeCriteria,setQtExcludeCriteria] = useState(ResData.criteria.exclusionCriteria.map((crit)=><input type='text' name='excludeCriteria' value={crit} /> ))
         const [fases,setFases] = useState(ResData.phases.map((fase)=><><label htmlFor="fases">Fase {fase.phaseNumber}</label><input type='text' name='fases' value={fase.description}/></> ))
        
          return(
            <>
              <Navbar/>
              <div className='container-page'>
                <h1 className="title-page">ALTERAR PESQUISA</h1>
                <h2 className='subtitle-page'>{ResData.title +" - #" +ResData.code}</h2>
                <div className="card-border" style={{margin:"2vw 0vw"}}>
                  <div className='form-new-research'>
                    <div className='session'>
                      <label htmlFor="title">Título</label>
                      <input type="text" name='title' value={ResData?.title} /> 
                    </div>
            
                    <div className='session'>
                      <div>
                          <label htmlFor="pacients">Pacientes</label>
                          <input type='number' name='pacients'  value={ResData.numberOfPatients}/> 
                      </div>
                      <div>
                          <label htmlFor="area">Área do estudo</label>
                          <input type="text" name='area' value={ResData.area} /> 
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
                            <textarea name='desc' value={ResData.description} /> 
                        </div>

                        <div className='session'>
                            <div>
                                <label htmlFor="begginDate">Data de inicio</label>
                                <input type='date' name='begginDate' value={ResData.studyDuration.startDate}/>
                            </div>
                            <div>
                                <label htmlFor="endDate">Data de encerramento</label>
                                <input type='date' name='endDate' value={ResData.studyDuration.endDate} />
                            </div>
                        </div>
                        
                        <div className='session'>
                            <p><strong>Fases</strong></p>
                            {fases}
                            <button onClick={()=>setFases([...fases,<><label>Fase {fases.length+1}</label><input type='text' name='fases' /><button onClick={()=>setFases(fases.slice(0,fases.length))}>-</button></>])}>+</button>
                        </div>

                        <div className='session'>
                            <label htmlFor="local"> Local da pesquisa (Apenas estado e município)</label>
                            <input type='text' name='local' value={ResData.location} /> 
                        </div>

                        <div className='session'>
                            <label htmlFor="documents"> Documentos (PDF): </label>
                            <div></div>
                            <button>+</button>
                        </div>
                    
                        <button type="submit" className="send-data">CONFIRMAR ALTERAÇÕES</button>
            
                  </div>
              </div> 
            </div> 
            </>
          )
}