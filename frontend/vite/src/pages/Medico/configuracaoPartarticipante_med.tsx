import Navbar from "../../components/Navbar";
import { useSearchParams, Link } from "react-router-dom";
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
                            <label className="nome-part-med">Nome</label>
                            <input className="input-nome"/>
                            <label className="email-part-med">Email</label>
                            <div className="div-email-genero">
                                <input className="input-email"/>
                                <select>
                                    <option selected>Gênero</option>
                                    <option value="1">Masculino</option>
                                    <option value="2">Feminio</option>
                                </select>
                            </div>
                            <label className="telefone-part-med">Telefone</label>
                            <div className="div-telefone">
                                <input className="input-telefone-dd"/>
                                <input className="input-telefone-numero"/>
                            </div>
                            <label htmlFor="titulo">Histórico Médico</label>
                            <textarea className="textarea-historico"></textarea>
                            <label htmlFor="content">Medicamentos em uso</label>
                            <textarea className="textarea-medicamentos"></textarea>
                            <div className="div-label-alergias-cirurgias">
                                <label>Alergias</label>
                                <label className="label-cirurgias">Cirurgias anteriores</label>
                            </div>
                            <div className="div-alergias-cirurgias">
                                <textarea className="textarea-alergia"></textarea>
                                <textarea className="textarea-cirurgias"></textarea>
                            </div>
                            <div className="div-adicionar-part">
                            <label>Resutados de exames recentes</label>
                            <div className="files-arrange-part">
                                <input type="file" name="files" multiple onChange={haddleFiles}/>
                                +
                            </div>
                            </div>
                            <div style={{display:"flex"}}>
                                {files?.map((f)=>(
                                    <div className="files-custom">{f.name}</div>
                                ))}
                            </div> 
                        </div>
                    </div>
                    <button className="button-send-notification">Salvar</button>
                </div>
        </>
    )
}