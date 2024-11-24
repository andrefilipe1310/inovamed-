
import { useState } from "react";
import Navbar from "../../components/Navbar";
import infoPesq from "../Representante/pesqPacientTest.json"


export default function RepNotificacoes(){

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
                            <label htmlFor="titulo">TÍTULO DA NOTIFICAÇÃO</label>
                            <input type="text" name="titulo" />
                            <label htmlFor="content">CONTEÚDO</label>
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
                            
                            
                            <div className="container-select">
                                <select name="selectTypeUser" id="" onChange={(e)=>{setCod(Number(e.target.value))}}>
                                    <option value="" disabled selected>Selecione a pesquisa</option>
                                    {infoPesq.map((info,index)=>(
                                        <option value={index}>{info.code}</option>
                                    ))}
                                </select>
                                <select name="selectWho" id="" >
                                <option value="" disabled selected>Selecione o paciente</option>
                                <option value="">Nenhum</option>
                                    {infoPesq[cod].patients.map((info)=>(
                                        <option value={info.id}>{info.name}</option>
                                    ))}
                                </select>
                                <select name="selectWho" id="" >
                                <option value="" disabled selected>Selecione o doutor</option>
                                <option value="">Nenhum</option>
                                    {infoPesq[cod].responsibleDoctors.map((info)=>(
                                        <option value={info}>{info}</option>
                                    ))}
                                </select>
                            </div>
                            
                        </div>
                    </div>
                    <button className="button-send-notification">ENVIAR NOTIFICAÇÃO</button>
                </div>
        </>
    )
}