import Navbar from "../../components/Navbar";
import { useSearchParams, Link } from "react-router-dom";
import { useState } from "react";
import dadospart from './participantesinfo_med.json';

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
        interface ParticipanteMed {
            id: number ;
            nome: string;
            idade: number;
            genero: string;
            email: string;
            telefonedd: string;
            telefonenumero: string;
          }
          const participanteMed:ParticipanteMed[] = dadospart
    return(
        <>
        <Navbar/>
        <div className='container-page'> 
                    <h1 className="title-page">PARTICIPANTES</h1>
                    {
                    participanteMed.filter((partMed) => partMed.id === +id)
                    .map((partMed) =>(
                    <div className="card-border" key={partMed.id}>
                        <div className="container-notfic-all">
                            <label className="nome-part-med">Nome</label>
                            <input className="input-nome" value={partMed.nome ?? 'Nome não encontrado'} disabled/>
                            <label className="email-part-med">Email</label>
                            <div className="div-email-genero">
                                <input className="input-email" value={partMed.email ?? 'Email não encontrado'} disabled/>
                                <input className="input-email" value={partMed.genero ?? 'Genero não encontrado'} disabled/>
                            </div>
                            <label className="telefone-part-med">Telefone</label>
                            <div className="div-telefone">
                                <input className="input-telefone-dd" value={partMed.telefonedd ?? 'Genero não encontrado'} disabled/>
                                <input className="input-telefone-numero" value={partMed.telefonenumero ?? 'Genero não encontrado'} disabled/>
                            </div>
                            <label htmlFor="titulo">Histórico Médico</label>
                            <textarea className="textarea-historico"></textarea>
                        </div>
                    </div>
                    ))}
                    <Link to={`/medico/participantes`}><button className="button-send-notification">Salvar</button></Link>
                </div>
        </>
    )
}