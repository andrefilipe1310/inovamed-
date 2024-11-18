import { useState } from "react";
import Navbar from "../../components/Navbar";
import { useSearchParams, Link } from "react-router-dom";
import pacTest from './listpac.json'
import studie from "./sigleStudieTest.json"


export default function ListaPac(){
    
    const [seachParams] = useSearchParams()
    const id = seachParams.get('id')

    interface studieInfo {
        title: String;
        code: Number;
    }
    
    interface MedicalHistory{
        id: number;
        conditions: string[];
        medications: string[];
    }

    interface Pacient {
        code: number;
        name: string;
        gender: string;
        birth: string;
        digitalSignatureConsent: boolean;
        responsibleDoctor: boolean;
        medicalHistory: MedicalHistory;
    }

    const pacInfoToAprove:Pacient[] = pacTest
    const pacInfoAproved:Pacient[] = pacTest
    const infoPesq:studieInfo = {
        title: studie.title,
        code: studie.code
    }

    const [selectedParticipantToAprove, setSelectedParticipantToAprove] = useState<Pacient>()
    const [selectedParticipantAproved, setSelectedParticipantAproved] = useState<Pacient>()
    const [toAproveReproval, setToAproveReproval] = useState<Boolean>(false)
    const [aprovedReproval, setAprovedReproval] = useState<Boolean>(false)
    
    

    

    return(
       <>
            <Navbar/>
            <div className="container-page">
                <h1 className="title-page">PARTICIPANTES</h1>
                <h2 className="subtitle-pac">{infoPesq.title+ " - #" + infoPesq.code}</h2>
                
                <div className="section-pac">
                    <h3>SOLICITAÇÕES</h3>
                    <div className="participant-list">
                    {pacInfoToAprove.map((pac) => (
                        <button key={pac.code} onClick={()=>{setSelectedParticipantToAprove(pac);setToAproveReproval(false)}}>
                        {pac.name}
                        </button>
                    ))}
                    </div>
                </div>

                { selectedParticipantToAprove ? 
                    <div className="edit-patient">
                        <h2>{selectedParticipantToAprove?.name}</h2>
                        <p><strong>Código: </strong>{selectedParticipantToAprove?.code}</p>
                        <p><strong>Idade: </strong>{new Date().getFullYear() - new Date(selectedParticipantToAprove?.birth).getFullYear()}  </p>
                        <p><strong>Gênero: </strong> {selectedParticipantToAprove?.gender}</p>
                        <div >
                            <button>✔️</button>
                            <button onClick={()=>setToAproveReproval(true)}>❌</button>
                        </div>
                    </div>
                    :
                    <></>
                }
                {
                    toAproveReproval == true ? 
                    <div className="reproval-motive">
                        <label htmlFor="motive">Por qual motivo o paciente não foi aceito na pesquisa?</label>
                        <textarea name="motive" id=""></textarea>
                        <button>FINALIZAR</button>
                        <button onClick={()=>setToAproveReproval(false)}>x</button>
                    </div>
                    :
                    <></>
                }

            <div className="section-pac">
                    <h3>APROVADOS</h3>
                    <div className="participant-list">
                    {pacInfoAproved.map((pac) => (
                        <button key={pac.code} onClick={()=>setSelectedParticipantAproved(pac)}>
                        {pac.name}
                        </button>
                    ))}
                    </div>
                </div>

                { selectedParticipantAproved ? 
                    <div className="edit-patient">
                        <h2>{selectedParticipantAproved?.name}</h2>
                        <p><strong>Código: </strong>{selectedParticipantAproved?.code}</p>
                        <p><strong>Idade: </strong>{new Date().getFullYear() - new Date(selectedParticipantAproved?.birth).getFullYear()}  </p>
                        <p><strong>Gênero: </strong> {selectedParticipantAproved?.gender}</p>
                        <div>
                            <button onClick={()=>setAprovedReproval(true)}>❌</button>
                        </div>
                    </div>
                    :
                    <></>
                }
                {
                    aprovedReproval == true ? 
                    <div className="reproval-motive">
                        <label htmlFor="motive">Por que o paciente está sendo desligado da pesquisa?</label>
                        <textarea name="motive" id=""></textarea>
                        <button>FINALIZAR</button>
                        <button onClick={()=>setAprovedReproval(false)}>x</button>
                    </div>
                    :
                    <></>
                }
            </div>
        </>
    )
}