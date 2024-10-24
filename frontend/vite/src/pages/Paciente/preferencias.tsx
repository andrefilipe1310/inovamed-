import Navbar from "../../components/Navbar";
import React, { useState } from 'react';
 
interface Preferencias {
    titulo: string | JSX.Element;
    desc: string;
}
 
export default function PacPreferencias() {
    const [preferencias, setPreferencias] = useState<Preferencias[]>([
        {
            titulo: "Permitir que os dados sejam visualizados pelo site para",
            desc: "Eu, autorizo a disponibilização dos resultados da minha participação neste estudo clínico para fins de pesquisa e desenvolvimento. " +
                  "Compreendo que os dados coletados poderão ser utilizados em publicações científicas, apresentações e relatórios, garantindo que minha identidade " +
                  "e informações pessoais permanecerão confidenciais e não serão divulgadas.",
        },
    ]);
 
    return (
        <>
            <Navbar />
            <div className='container-page'>
                <div className='card-border'>
                    <div className="container-card">
                        <h1>PREFERÊNCIAS</h1>
                        <h2>AUTORIZAÇÕES</h2>
                        <div className='card-pesquisas'>
                            {preferencias.map((info, index) => (
                                <div key={index} className='pesq-detail'>
                                    <p className='title-pesq'>{info.titulo}</p>
                                    <p className='desc-pesq'>{info.desc}</p>
                                    <label>
                                        <input
                                            type="checkbox"
                                        />
                                        <span> Concordo</span>
                                    </label>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
