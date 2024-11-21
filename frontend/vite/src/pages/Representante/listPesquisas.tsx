
import Navbar from "../../components/Navbar";
import { Link } from "react-router-dom";

import { useEffect, useState } from "react";
import api from "../../config/axiosConfig";



export default function RepListaPesquisas(){

    const [errorMessage, setErrorMessage] = useState<string | null>(null)
    const [pesqInfo, setPesqInfo] = useState<{title:string,code:number}[]>([])

    const handleFindAllSearch = async () => {
        api.get("/research/feature")
        .then(response => {
            if(!response.data[0]){
                return null
            }
            
            if (!response.data[0].code) {
                
              return null  
            }
            console.log(response.data)
            if (!response.data[0].title){
            
                return null
            }
            console.log(response.data)
            
            setPesqInfo(response.data)
                
            
        })
        .catch(error => {
            setErrorMessage("Servidor com problema para renderizar suas pesquisas")
            console.error("Erro na busca por pesquisas:", error);
        })

    }
    
    
    useEffect(()=>{handleFindAllSearch()},[])
    return(
        <>
        <Navbar/>
        <div className="container-page">
            <h1 className="title-page">MINHAS PESQUISAS</h1>
            {errorMessage != null && <p style={{color:'red'}}>{errorMessage}</p>}
            {(pesqInfo.length == 0 && errorMessage == null )&&<p>Nenhuma pesquisa a mostrar</p>}
            <div className="container-pesq">      
               {pesqInfo.length>0 && pesqInfo.map((pesq)=>(
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
        </>
    )
}