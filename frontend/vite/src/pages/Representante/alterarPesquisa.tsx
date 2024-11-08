import { useEffect, useState } from 'react';
import Navbar from '../../components/Navbar';
import { useSearchParams } from "react-router-dom";
import api from '../../config/axiosConfig';
import { ResearchResponseDTO, Phase } from '../../types/ResearchTypes';


export default function RepAlterarPesquisa() {
  const [searchParams] = useSearchParams();
  const code = searchParams.get('id');

  const [research, setResearch] = useState<ResearchResponseDTO>();
  const [qtMed, setqtMed] = useState<string[]>([]);
  const [qtRespInst, setQtRespInst] = useState<string[]>([]);
  const [qtIncludeCriteria, setQtIncludeCriteria] = useState<string[]>([]);
  const [qtExcludeCriteria, setQtExcludeCriteria] = useState<string[]>([]);
  const [fases, setFases] = useState<Phase[]>([]);

  const handleFindByCodeResearch = async () => {
    try {
      const response = await api.get(`/research/code/${code}`);
      if (response.data) {
        setResearch(response.data);
        if (research == null && research == undefined) {
          return null;
        }
        setqtMed(response.data.responsibleDoctors || []);
        setQtRespInst(response.data.institutions || []);
        setQtIncludeCriteria(response.data.criteria?.inclusion || []);
        setQtExcludeCriteria(response.data.criteria?.exclusion || []);
        setFases(response.data.phases || []);
      }
    } catch (error) {
      console.error(`Erro ao buscar pesquisa: ${error}`);
    }
  };


  useEffect(() => {
    if (code) {
      handleFindByCodeResearch();
    }
  }, [code]);

  return (
    <>
      <Navbar />
      <div className='container-page'>
        <h1 className="title-page">ALTERAR PESQUISA</h1>
        {research && (
          <div className='principal'>
            <h2 className='subtitle-page'>{research.title} - #{research.code}</h2>
            <div className="card-border" style={{ margin: "2vw 0vw" }}>
              <div className='form-new-research'>
                <div className='session'>
                  <label htmlFor="title">Título</label>
                  <input type="text" name='title' defaultValue={research.title || ''} />
                </div>

                <div className='session'>
                  <label htmlFor="pacients">Pacientes</label>
                  <input type='number' name='pacients' defaultValue={research.numberOfPatients || 0} />
                </div>
                <div className='session'>
                  <label htmlFor="area">Área do estudo</label>
                  <input type="text" name='area' defaultValue={research.area || ''} />
                </div>

                <div className='session'>
                  <label htmlFor="respMed">Médico responsável</label>
                  {qtMed.map((med, index) => (
                    <input key={index} type='text' name='respMed' defaultValue={med} />
                  ))}
                  <button onClick={() => setqtMed([...qtMed, ''])}>+</button>
                </div>

                <div className='session'>
                  <label htmlFor="respInst">Instituição responsável</label>
                  {qtRespInst.map((inst, index) => (
                    <input key={index} type='text' name='respInst' defaultValue={inst} />
                  ))}
                  <button onClick={() => setQtRespInst([...qtRespInst, ''])}>+</button>
                </div>

                <div className='session'>
                  <label htmlFor="includeCriteria">Critérios de Inclusão: </label>
                  {qtIncludeCriteria.map((crit, index) => (
                    <input key={index} type='text' name='includeCriteria' defaultValue={crit} />
                  ))}
                  <button onClick={() => setQtIncludeCriteria([...qtIncludeCriteria, ''])}>+</button>
                  
                  <label htmlFor="excludeCriteria">Critérios de Exclusão: </label>
                  {qtExcludeCriteria.map((crit, index) => (
                    <input key={index} type='text' name='excludeCriteria' defaultValue={crit} />
                  ))}
                  <button onClick={() => setQtExcludeCriteria([...qtExcludeCriteria, ''])}>+</button>
                </div>

                <div className='session'>
                  <label htmlFor="desc">Descrição da pesquisa</label>
                  <textarea name='desc' defaultValue={research.description || ''} />
                </div>

                <div className='session'>
                  <label htmlFor="begginDate">Data de início</label>
                  <input type='date' name='begginDate' defaultValue={research.studyDuration?.start || ''} />
                  
                  <label htmlFor="endDate">Data de encerramento</label>
                  <input type='date' name='endDate' defaultValue={research.studyDuration?.end || ''} />
                </div>

                <div className='session'>
                  <p><strong>Fases</strong></p>
                  {fases.map((fase, index) => (
                    <div key={index}>
                      <label>Fase {fase.number}</label>
                      <input type='text' name='fases' defaultValue={fase.description} />
                    </div>
                  ))}
                  <button onClick={() => setFases([...fases, { number: fases.length + 1, title: '', description: '' }])}>+</button>
                </div>

                <button type="submit" className="send-data">CONFIRMAR ALTERAÇÕES</button>
              </div>
            </div>
          </div>
        )}
      </div>
    </>
  );
}
