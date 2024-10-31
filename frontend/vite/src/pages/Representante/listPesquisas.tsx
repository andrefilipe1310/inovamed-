
import Navbar from "../../components/Navbar";
import { Link } from "react-router-dom";
import pesquisaEntity from "./pesquisaTest.json"
import representanteEntity from "./representanteTest.json"

export default function RepListaPesquisas(){
    
    const pesqInfo = pesquisaEntity //find all reseachs

    return(
        <>
        <Navbar/>
        <div className="container-page">
            <h1 className="title-suas-pesquisas">MINHAS PESQUISAS</h1>
            <div className="container-pesq">
                {pesqInfo.map((pesq)=>(
                    <div className="card-border">
                        <div  className="container-card-pesqlist">
                            <Link to={`/representante/infoPesquisas?id=${pesq.id}`} className="link-to-pesq">
                                <h2>{pesq.title}</h2>
                                <p>#{pesq.code}</p>
                            </Link>
                        </div>
                    </div>
                ))}
            </div>
        </div>
        </>
    )
}