import Navbar from "../../components/Navbar";
import { useSearchParams, Link } from "react-router-dom";
import { ResearchResponseDTO } from "../../types/ResearchTypes";
import { useState } from "react";
export default function MedParticipantesConfig(){
    const [seachParams] = useSearchParams()
    const id = seachParams.get('id')
    const [cod,setCod] = useState(0)
        const [files, setFiles] = useState<File[]>()

        const haddleFiles = (e: React.ChangeEvent<HTMLInputElement>)=>{
            const theFiles = e.target.files;
            if (theFiles) {
            // Convertendo o FileList para um array de File
            const fileArray = Array.from(theFiles);

            // Atualizando o estado com os arquivos selecionados
            setFiles((prevFiles) => prevFiles ? [...prevFiles, ...fileArray]: [...fileArray]);
            }
        }
    return(
        <>
        <Navbar/>

        <div className='container-page'> 
                    <h1 className="title-page">NOTIFICAÇÕES</h1>
                    <div className="card-border">
                        <div className="container-notfic-all">
                            <label htmlFor="titulo">Histórico Médico</label>
                            <input type="text" name="titulo" />
                            <label htmlFor="content">Medicamentos em uso</label>
                            <textarea name="content" id="content"></textarea>
                            <label htmlFor="files" style={{alignSelf:"center"}}>ANEXOS</label>
                            <div className="files-arrange" style={{alignSelf:"center"}}>
                                <input type="file" name="files" multiple onChange={haddleFiles}/>
                                +
                            </div>
                            <div style={{display:"flex"}}>
                                {files?.map((f)=>(
                                    <div className="files-custom">{f.name}</div>
                                ))}
                            </div> 
                        </div>
                    </div>
                    <button className="button-send-notification">ENVIAR NOTIFICAÇÃO</button>
                </div>
        </>
    )
}