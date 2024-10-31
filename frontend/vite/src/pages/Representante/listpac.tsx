import { useState } from "react";
import Navbar from "../../components/Navbar";
import pacTest from './listpac.json'

export default function ListaPac(){

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

    const [selectedParticipantToAprove, setSelectedParticipantToAprove] = useState<Pacient>()
    const [selectedParticipantAproved, setSelectedParticipantAproved] = useState<Pacient>()
    

    

    return(
       <>
            <Navbar/>
            <div className="container-page">
                <h1>PARTICIPANTES</h1>
                <h2>Mecanismos Moleculares e Genéticos no Alzheimer: Novas Terapias - #0058</h2>
                
                <div className="section">
                    <h3>SOLICITAÇÕES</h3>
                    <div className="participant-list">
                    {pacInfoToAprove.map((pac) => (
                        <button key={pac.code} onClick={()=>setSelectedParticipantToAprove(pac)}>
                        {pac.name}
                        </button>
                    ))}
                    </div>
                </div>

                { selectedParticipantToAprove ? 
                    <div>
                        <h2>{selectedParticipantToAprove?.name}</h2>
                        <p><strong>Código: </strong>{selectedParticipantToAprove?.code}</p>
                        <p><strong>Idade: </strong>{new Date().getFullYear() - new Date(selectedParticipantToAprove?.birth).getFullYear()}  </p>
                        <p><strong>Gênero: </strong> {selectedParticipantToAprove?.gender}</p>
                        <div>
                            <button>✔️</button>
                            <button>❌</button>
                        </div>
                    </div>
                    :
                    <></>
                }

            <div className="section">
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
                    <div>
                        <h2>{selectedParticipantAproved?.name}</h2>
                        <p><strong>Código: </strong>{selectedParticipantAproved?.code}</p>
                        <p><strong>Idade: </strong>{new Date().getFullYear() - new Date(selectedParticipantAproved?.birth).getFullYear()}  </p>
                        <p><strong>Gênero: </strong> {selectedParticipantAproved?.gender}</p>
                        <div>
                            <button>✔️</button>
                            <button>❌</button>
                        </div>
                    </div>
                    :
                    <></>
                }
            </div>
        </>
    )
}