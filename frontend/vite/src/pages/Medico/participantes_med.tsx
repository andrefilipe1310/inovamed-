import Navbar from "../../components/Navbar";
import participanteMed from './participantesinfo';
import { Link } from "react-router-dom";
export default function MedParticipantes(){
    return(
        <>
         <Navbar/>
         <div className='container-page-medico'>
            <div className="funcoes-medico">
            <h1 className='h1notificacao'>PESQUISAS DISPONÍVEIS</h1>
                <div className="container-card-medico">
                {participanteMed.map((partMed) => (
                    <div className="div-dados-pesquisa-medico" key={partMed.id}>
                        <div className="div-background">
                            <p>Nome: {partMed.nome}</p>
                            <div className='linha-card'>
                                <p className="p2-card-medico">#00{partMed.id}</p>
                            </div>
                        </div>
                    </div>
                    ))}
                </div>
            </div>
        </div>
        </>
    )
}