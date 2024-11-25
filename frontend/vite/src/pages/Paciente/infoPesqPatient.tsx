import Navbar from "../../components/Navbar";
import { useSearchParams, Link } from "react-router-dom";
import { ResearchResponseDTO } from "../../types/ResearchTypes";
import api from "../../config/axiosConfig";
import { useState, useEffect } from "react";

export default function InfoPesqPatient(){
    const [seachParams] = useSearchParams()
    const id = seachParams.get('id')
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
                    <h1 className="title-pesquisa">{research?.title} - {research?.code}</h1>
                    <h2 className="subtitle-pesquisa">Pesquisa da área de {research?.area}</h2>
                    <div style={{ display: "flex", width: "20vw", justifyContent: "space-evenly" }}>
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
                        <p><strong>Nº de paciente</strong> {research?.numberOfPatients}</p>
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
                                 <div key={index}>
                                 <p>{attachment.name}</p>
                                 <button onClick={() => downloadAttachment(attachment.name, attachment.archive)}>
                                   Baixar
                                 </button>
                                 </div>
                            ))}
                        </div>
                        {error && <p style={{ color: 'red' }}>{error}</p>}
                    </div>
                </div>}{/* principal */}
            </div>

        </>
    )
}
    
