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
        
          return(
            <>
              <Navbar/>
              <div className='container-page'>
              <form className='form-new-research'>
                <div className='session'>
                  <label htmlFor="title">Título</label>
                  <input type="text" name='title' value={ResData?.title} /> 
                </div>
        
                <div className='session'>
                  <label htmlFor="pacients">Pacientes</label>
                  <input type='number' name='pacients' /> 
                  <label htmlFor="area">Área do estudo</label>
                  <input type="text" name='area' /> 
                </div>
        
                <div className='session'>
                  <label htmlFor="respMed">Médico responsável</label>
                  <input type='text' name='respMed' /> 
                  <button>+</button>
                </div>
        
                <div className='session'>
                  <label htmlFor="respInst">Instituição responsável</label>
                  <input type='text' name='respInst' /> 
                  <button>+</button>
                </div>
        
                <div className='session'>
                  <label htmlFor="criteria">Critérios de elegibilidade</label>
                  <input type='text' name='criteria' /> 
                  <button>+</button>
                </div>
        
                <div className='session'>
                  <label htmlFor="desc">Descrição da pesquisa</label>
                  <input type='text' name='desc' /> 
                  <button>+</button>
                </div>
        
                <div className='session'>
                  <label htmlFor="begginDate">Data de inicio</label>
                  <input type='date' name='begginDate' /> 
                  <label htmlFor="endDate">Data de encerramento</label>
                  <input type='date' name='endDate' /> 
                </div>
                
                <div className='session'>
                  <label htmlFor="fases">Fase 1</label>
                  <input type='text' name='fases' /> 
                  <button>+</button>
                </div>
        
                <div className='session'>
                  <label htmlFor="local"> Local da pesquisa (Apenas estado e município)</label>
                  <input type='text' name='local' /> 
                  <button>+</button>
                </div>
        
                <div className='session'>
                  <label htmlFor="documents"> Documentos (PDF)</label>
                  <button>+</button>
                </div>
              
                <button type="submit">CRIAR PESQUISA</button>
        
              </form>
            </div>  
            </>
          )
}