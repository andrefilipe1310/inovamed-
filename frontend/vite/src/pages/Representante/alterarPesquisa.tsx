import { FormEvent, useEffect, useState } from 'react';
import Navbar from '../../components/Navbar';
import { useSearchParams } from "react-router-dom";
import api from '../../config/axiosConfig';
import { ResearchResponseDTO, ResearchUpdateDTO, Phase } from '../../types/ResearchTypes';

export default function RepAlterarPesquisa() {
  const [searchParams] = useSearchParams();
  const code = searchParams.get('id');
  
  const [research, setResearch] = useState<ResearchResponseDTO | null>(null);
  const [qtMed, setQtMed] = useState<string[]>([]);
  const [qtRespInst, setQtRespInst] = useState<string[]>([]);
  const [qtIncludeCriteria, setQtIncludeCriteria] = useState<string[]>([]);
  const [qtExcludeCriteria, setQtExcludeCriteria] = useState<string[]>([]);
  const [fases, setFases] = useState<Phase[]>([]);

  const handleFindByCodeResearch = async () => {
    try {
      const response = await api.get(`/research/code/${code}`);
      if (response.data) {
        setResearch(response.data);
        setQtMed(response.data.responsibleDoctors || []);
        setQtRespInst(response.data.institutions || []);
        setQtIncludeCriteria(response.data.criteria?.inclusion || []);
        setQtExcludeCriteria(response.data.criteria?.exclusion || []);
        setFases(response.data.phases || []);
      }
    } catch (error) {
      console.error(`Erro ao buscar pesquisa: ${error}`);
    }
  };

  const handleUpdateResearch = async (e: FormEvent) => {
    e.preventDefault();

    const researchUpdateDTO: ResearchUpdateDTO = {
      title: research?.title||"",
      area: research?.area||"",
      numberOfPatients: research?.numberOfPatients||0,
      availableVacancies: research?.availableVacancies||0,
      responsibleDoctors: qtMed,
      institutions: qtRespInst,
      description: research?.description||"",
      criteria: {
        inclusion: qtIncludeCriteria,
        exclusion: qtExcludeCriteria,
      },
      studyDuration: research?.studyDuration||{start:"",end:""},
      phases: fases,
      currentPhase: research?.currentPhase||0,
      location: research?.location||"",
      attachments: research?.attachments||[],
    };

    try {
      const response = await api.put(`/research/code/${code}`, researchUpdateDTO);
      console.log(response.data);
    } catch (error) {
      console.error(`Erro ao atualizar pesquisa: ${error}`);
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
              <form className='form-new-research' onSubmit={handleUpdateResearch}>
                <div className='session'>
                  <label htmlFor="title">Título</label>
                  <input
                    type="text"
                    name='title'
                    value={research.title || ''}
                    onChange={(e) => setResearch(prev => prev ? { ...prev, title: e.target.value } : prev)}
                  />
                </div>

                <div className='session'>
                  <label htmlFor="pacients">Pacientes</label>
                  <input
                    type='number'
                    name='pacients'
                    value={research.numberOfPatients || 0}
                    onChange={(e) => setResearch(prev => prev ? { ...prev, numberOfPatients: +e.target.value } : prev)}
                  />
                </div>

                <div className='session'>
                  <label htmlFor="area">Área do estudo</label>
                  <input
                    type="text"
                    name='area'
                    value={research.area || ''}
                    onChange={(e) => setResearch(prev => prev ? { ...prev, area: e.target.value } : prev)}
                  />
                </div>

                <div className='session'>
                  <label htmlFor="respMed">Médico responsável</label>
                  {qtMed.map((med, index) => (
                    <input
                      key={index}
                      type='text'
                      name='respMed'
                      value={med}
                      onChange={(e) => {
                        const newMedList = [...qtMed];
                        newMedList[index] = e.target.value;
                        setQtMed(newMedList);
                      }}
                    />
                  ))}
                  <button type="button" onClick={() => setQtMed([...qtMed, ''])}>+</button>
                </div>

                <div className='session'>
                  <label htmlFor="respInst">Instituição responsável</label>
                  {qtRespInst.map((inst, index) => (
                    <input
                      key={index}
                      type='text'
                      name='respInst'
                      value={inst}
                      onChange={(e) => {
                        const newInstList = [...qtRespInst];
                        newInstList[index] = e.target.value;
                        setQtRespInst(newInstList);
                      }}
                    />
                  ))}
                  <button type="button" onClick={() => setQtRespInst([...qtRespInst, ''])}>+</button>
                </div>

                <div className='session'>
                  <label htmlFor="includeCriteria">Critérios de Inclusão: </label>
                  {qtIncludeCriteria.map((crit, index) => (
                    <input
                      key={index}
                      type='text'
                      name='includeCriteria'
                      value={crit}
                      onChange={(e) => {
                        const newIncludeList = [...qtIncludeCriteria];
                        newIncludeList[index] = e.target.value;
                        setQtIncludeCriteria(newIncludeList);
                      }}
                    />
                  ))}
                  <button type="button" onClick={() => setQtIncludeCriteria([...qtIncludeCriteria, ''])}>+</button>

                  <label htmlFor="excludeCriteria">Critérios de Exclusão: </label>
                  {qtExcludeCriteria.map((crit, index) => (
                    <input
                      key={index}
                      type='text'
                      name='excludeCriteria'
                      value={crit}
                      onChange={(e) => {
                        const newExcludeList = [...qtExcludeCriteria];
                        newExcludeList[index] = e.target.value;
                        setQtExcludeCriteria(newExcludeList);
                      }}
                    />
                  ))}
                  <button type="button" onClick={() => setQtExcludeCriteria([...qtExcludeCriteria, ''])}>+</button>
                </div>

                <div className='session'>
                  <label htmlFor="desc">Descrição da pesquisa</label>
                  <textarea
                    name='desc'
                    value={research.description || ''}
                    onChange={(e) => setResearch(prev => prev ? { ...prev, description: e.target.value } : prev)}
                  />
                </div>

                <div className='session'>
                  <label htmlFor="begginDate">Data de início</label>
                  <input
                    type='date'
                    name='begginDate'
                    value={research.studyDuration?.start || ''}
                    onChange={(e) => setResearch(prev => prev ? { ...prev, studyDuration: { ...prev.studyDuration, start: e.target.value } } : prev)}
                  />
                  
                  <label htmlFor="endDate">Data de encerramento</label>
                  <input
                    type='date'
                    name='endDate'
                    value={research.studyDuration?.end || ''}
                    onChange={(e) => setResearch(prev => prev ? { ...prev, studyDuration: { ...prev.studyDuration, end: e.target.value } } : prev)}
                  />
                </div>

                <div className='session'>
                  <p><strong>Fases</strong></p>
                  {fases.map((fase, index) => (
                    <div key={index}>
                      <label>Fase {fase.number}</label>
                      <input
                        type='text'
                        name='fases'
                        value={fase.description}
                        onChange={(e) => {
                          const newPhases = [...fases];
                          newPhases[index] = { ...fase, description: e.target.value };
                          setFases(newPhases);
                        }}
                      />
                    </div>
                  ))}
                  <button type="button" onClick={() => setFases([...fases, { number: fases.length + 1, title: '', description: '' }])}>+</button>
                </div>

                <button type="submit" className="send-data">CONFIRMAR ALTERAÇÕES</button>
              </form>
            </div>
          </div>
        )}
      </div>
    </>
  );
}
