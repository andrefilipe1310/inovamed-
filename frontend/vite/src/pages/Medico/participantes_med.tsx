import { Link } from "react-router-dom";
import Navbar from "../../components/Navbar";
import dadospart from './participantesinfo_med.json';
export default function MedParticipantes(){
    interface ParticipanteMed {
        id: number;
        nome: string;
        idade: number;
        genero: string;
      }

      const participanteMed:ParticipanteMed[] = dadospart
    return(
        <>
         <Navbar/>
         <div className='container-page-medico-part'>
            <div className="funcoes-medico-part">
                <h1 className='h1notificacao'>PESQUISAS DISPONÍVEIS</h1>
                <div className="container-card-part-med">
                    <div className="container-card-part">
                        {participanteMed.map((partMed) => (
                        <div className="div-dados-part-med" key={partMed.id}>
                            <div className="div-background-part">
                                <div className="dados-card-part-med">
                                    <div className="p-participantes">
                                        <p className="p-esquerdo">Nome:</p>
                                        <p className="p-direito">{partMed.nome}</p>
                                    </div>
                                    <div className="p-participantes">
                                        <p className="p-esquerdo">Idade:</p>
                                        <p className="p-direito">{partMed.idade}</p>
                                    </div>
                                    <div className="p-participantes">
                                        <p className="p-esquerdo">Gênero:</p>
                                        <p className="p-direito">{partMed.genero}</p>
                                    </div>
                                    <div className="div-link-part">
                                        <Link to={`/medico/participantesconfiguracao?id=${partMed.id}`}><button className="button-link-part"><img src="../../public/config-icon.png" className="img-link-part"/></button></Link>
                                        <button className="button-link-part"><img src="../../public/fechar.png" className="img-link-part"/></button>
                                    </div>                         
                                </div>
                                <div className='linha-card-part-med'>
                                    <p className="p2-card-medico">#00{partMed.id}</p>
                                </div>
                            </div>
                        </div>
                    ))}
                    </div>
                </div>
            </div>
        </div>
        </>
    )
}