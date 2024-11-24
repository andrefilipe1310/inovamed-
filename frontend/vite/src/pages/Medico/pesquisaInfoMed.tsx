import { Link, useSearchParams } from "react-router-dom"
import Navbar from "../../components/Navbar"
import { useEffect, useState } from "react"
import api from "../../config/axiosConfig"
import { ResearchResponseDTO } from "../../types/ResearchTypes"


export default function PesquisaInfoMed(){
    const [seachParams] = useSearchParams()
    const id = seachParams.get('id')
    const [pesqInfo, setPesqInfo] = useState<any[]>([]);
    const [cod, setCod] = useState<number>(-1);
    let [research, setResearch] = useState<ResearchResponseDTO>()
    const [error, setError] = useState("")

    const handleFindByCodeResearch = async () => {
        api.get(`/research/code/${id}`)
            .then(response => {

                if (!response.data) {
                    return null
                }
                setResearch(response.data)




            })
            .catch(error => {
                setError(`Erro na busca por pesquisas: ${id}`)
                console.error(`Erro na busca por pesquisas: ${id}`, error)
            })


    }

    // Função para baixar o PDF
    const downloadAttachment = (name:string, archiveBase64:Base64URLString) => {
        // Converte a string Base64 para um Blob
        const byteCharacters = atob(archiveBase64);
        const byteNumbers = Array.from(byteCharacters, char => char.charCodeAt(0));
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: 'application/pdf' });
    
        // Cria um link temporário para baixar o arquivo
        const link = document.createElement('a');
        link.href = URL.createObjectURL(blob);
        link.download = name;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      };

    useEffect(() => {
        if (id) {
            handleFindByCodeResearch();
        }
    }, [id]);


    return (

        <>
            <Navbar />
            <div className="container-page" >
                {(research == null) && <div><p style={{ color: 'red' }}>{error}</p></div>}
                {(research != null) && <div className="principal">
                    <div className="container-pesq-all" style={{display:"flex", flexDirection:"column", width:"100%", alignItems:"center"}}>
                    <h1 className="title-pesquisa" style={{textAlign:"center"}}>{research?.title} - {research?.code}</h1>
                    <h2 className="subtitle-pesquisa">Pesquisa da área de {research?.area}</h2>
                    <div className="container-links-pesq">
                       
                    </div>
                    <div className="section-1">
                        <p><strong>Nº de pacientes:</strong> {research?.numberOfPatients}</p>
                        <p><strong>Médico resposnável:</strong> {research?.responsibleDoctors.map((doc) => (doc + ', '))}</p>
                    </div>
                    <p style={{ width: "80%" }}><strong>Instituição responsável:</strong> {research?.institutions.map((inst, index) => (<p style={{ display: "inline" }}>{inst}{index === research?.institutions.length - 1 ? '' : ', '}</p>))}</p>
                    <p style={{ width: "80%" }}><strong>Descrição:</strong> {research?.description}</p>
                    <div className="section-2">
                        <strong>Critérios de elegibilidade:</strong>
                        <div>
                            {/* <p> <strong>Idade: </strong>{research?.criteria.ageRange }</p> */}

                            <p><strong>Critério de inclusão:</strong> {research?.criteria.inclusion.map((rc) => (
                                <li>{rc}</li>
                            ))}
                            </p>
                            <p><strong>Critério de exclusão:</strong> {research?.criteria.exclusion.map((ec) => (
                                <li>{ec}</li>
                            ))}
                            </p>
                        </div>
                    </div>

                    <p style={{ width: "80%" }}><strong>Data de inicio e fim:</strong> {`${research?.studyDuration?.start[2]}/${research?.studyDuration?.start[1]}/${research?.studyDuration?.start[0]}`} à {`${research?.studyDuration?.end[2]}/${research?.studyDuration?.end[1]}/${research?.studyDuration?.end[0]}`}</p>
                    <div className="section-3">
                        <strong>Fases da pesquisa:</strong>
                        {research?.phases.map((fase) => <li><strong>Fase {fase.number}:</strong> {fase.description}</li>)}
                    </div>
                    <p style={{ width: "80%" }}><strong>Fase atual: </strong> Fase {research?.currentPhase}</p>
                    <p style={{ width: "80%" }}><strong>Localização: </strong>{research?.location}</p>
                    <div className="section-4">
                        <strong>Documentos:</strong>
                        <div>
                            {(research == null || research == undefined) && <div>Nenhum arquivo para baixar</div> } 
                            {research?.attachments.map((attachment, index) => (
                                 <div key={index} className="div-baixar">
                                 <p>{attachment.name}</p>
                                 <button onClick={() => downloadAttachment(attachment.name, attachment.archive)}>
                                   Baixar
                                 </button>
                                 </div>
                            ))}
                        </div>

                        <select
                name="selectWho"
                onChange={(e) =>
                  setNotification((prev) => ({
                    ...prev,
                    patientsId: [Number(e.target.value)],
                  }))
                }
              >
                <option value="-1">Selecione o paciente</option>
                {cod >= 0 &&
                  pesqInfo[cod]?.patients.map((info: any) => (
                    <option value={info.id} key={info.id}>
                      {info.name}
                    </option>
                  ))}
              </select>
                        {research != null && research != null && (
                            <button >Candidatar</button>
                        )}
                        
                        {error && <p style={{ color: 'red' }}>{error}</p>}
                    </div>
                </div>
                </div>}{/* principal */}
            </div>
        </>
        )
}

