import Navbar from "../../components/Navbar";
import { useSearchParams, Link } from "react-router-dom";
import pesq from './pesquisaTest.json'

export default function RepPesquisaInfo(){

    const [seachParams] = useSearchParams()
    const id = seachParams.get('id')

    const pesqInfo = pesq.find((p)=> p.id.toString() === id)

    console.log(id)
    return(
        <>
        <Navbar/>
        <div className="container-page" >

            <h1 className="title-pesquisa">{pesqInfo?.title} - {pesqInfo?.code}</h1>
            <h2 className="subtitle-pesquisa">Pesquisa da área de {pesqInfo?.area}</h2>
            <div style={{display:"flex", width:"20vw", justifyContent:"space-evenly"}}>
                <div className="card-border">
                    <div className="container-button">
                        <Link to={`/representante/alterarpesquisa?id=${pesqInfo?.id}`} className="button-to-edit">a</Link>
                    </div>
                </div>
                <div className="card-border">
                    <div className="container-button">
                        <Link to={`/representante/alterarpesquisa?id=${pesqInfo?.id}`} className="button-to-edit">a</Link>
                    </div>
                </div>
            </div>
            <div className="section-1">
                <p><strong>Nº de pacientes:</strong> {pesqInfo?.numberOfPatients}</p>
                <p><strong>Médico resposnável:</strong> {pesqInfo?.responsibleDoctors.map((doc)=>(doc+', '))}</p>
            </div>
            <p style={{width:"80%"}}><strong>Instituição responsável:</strong> {pesqInfo?.institutions.map((inst,index)=>(<p style={{display:"inline"}}>{inst}{index === pesqInfo?.institutions.length - 1 ? '' : ', '}</p>))}</p>
            <p style={{width:"80%"}}><strong>Descrição:</strong> {pesqInfo?.description}</p>
            <div className="section-2">
                <strong>Critério de elegibilidade:</strong>
                <div>
                    <li>Idade: {pesqInfo?.criteria.ageRange}</li>
                    <li>Critério de inclusão: {pesqInfo?.criteria.requiredCondition}</li>
                    <li>Critério de exclusão: {pesqInfo?.criteria.exclusionCriteria}</li>
                </div>
            </div>
            <p style={{width:"80%"}}><strong>Data de inicio e fim:</strong> {pesqInfo?.studyDuration.startDate} à {pesqInfo?.studyDuration.endDate}</p>
            <div className="section-3">
                <strong>Fases da pesquisa:</strong>
                {pesqInfo?.phases.map((fase) => <li><strong>Fase {fase.phaseNumber}:</strong> {fase.description}</li>)}
            </div>
            <p style={{width:"80%"}}><strong>Fase atual: </strong> Fase {pesqInfo?.currentPhase}</p>
            <p style={{width:"80%"}}><strong>Localização: </strong>{pesqInfo?.location}</p>
            <div className="section-4">
                <strong>Documentos:</strong>
                <div>
                    {pesqInfo?.attachments.map((doc)=><button>{doc.fileName}</button>)}
                </div>
            </div>
        </div>
        
        </>
    )
}