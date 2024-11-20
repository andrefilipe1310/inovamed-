import { useEffect, useState } from "react";
import Navbar from "../../components/Navbar";
import api from "../../config/axiosConfig";
import listapesquisa from "./listaPacientePesquisas_med.json";
import { Link } from "react-router-dom";
import { researchFeatures } from "../../types/ResearchTypes";


export default function MedListPacientesPesquisas() {
  interface listapesq {
    id: number;
    titulo: string;
  }
  const [researchs, setResearchs] = useState<researchFeatures[]>([]);

  const handleFindResearch = async () => {
    api
      .get("/research/feature-all")
      .then((response) => {
        console.log(response.data);
        setResearchs(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    handleFindResearch();
  }, []);

  const listapesq: listapesq[] = listapesquisa;

  return (
    <>
      <Navbar />
      <div className="container-page-medico">
        <div className="funcoes-medico">
          <h1 className="h1notificacao">PESQUISAS DISPONÍVEIS</h1>
          <div className="container-card-medico">
            
            <div className="container-page">
            
        
           
            <div className="container-pesq">      
               {researchs.length>0 && researchs.map((pesq)=>(
                    <div className="card-border">
                        <div  className="container-card-pesqlist">
                            <Link to={`/representante/infoPesquisas?id=${pesq.code}`} className="link-to-pesq">
                                <h2>{pesq.title}</h2>
                                <p>#{pesq.code}</p>
                            </Link>
                        </div>
                    </div>
                ))}
            </div>
            <Link to="/representante/novapesquisa" className="button-nova-pesquisa"> NOVA PESQUISA</Link>
        </div>

            </div>
          </div>
        </div>
    </>
  );
}
