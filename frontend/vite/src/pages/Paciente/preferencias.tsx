import Navbar from '../../components/Navbar';

interface Preferencias {
    titulo: string | JSX.Element;
    desc: string;
}

const preferencias: Preferencias[] = [
    {
        titulo: "Permitir que os dados sejam visualizados pelo site para",
        desc: "Eu, autorizo a disponibilização dos resultados da minha participação neste estudo clínico para fins de pesquisa e desenvolvimento. " +
              "Compreendo que os dados coletados poderão ser utilizados em publicações científicas, apresentações e relatórios, garantindo que minha identidade " +
              "e informações pessoais permanecerão confidenciais e não serão divulgadas.",
    },
    {
        titulo: "Receber notificações sobre novos estudos",
        desc: "Eu, autorizo o envio de notificações sobre novos estudos e pesquisas que possam ser do meu interesse, por meio do meu e-mail ou telefone.",
    },
    {
        titulo: "Participar de pesquisas de satisfação",
        desc: "Eu, concordo em participar de pesquisas de satisfação sobre a experiência no site, contribuindo para melhorias futuras.",
    },
];

export default function PacPreferencias() {
    const handleSave = () => {
        console.log("Preferências salvas!");
    };

    return (
        <>
            <Navbar />
        
            <div className='container-page'>
                <h1 className="h1notificacaopac" style={{ color: '#028275' }}>PREFERÊNCIAS</h1>
                <div className='card-border'>
                    <div className="container-card">
                        <h2>AUTORIZAÇÕES</h2>
                        {preferencias.map((info, index) => (
                            <div key={index} className='pesq-detailpac' style={{ margin: '5px 0' }}>
                                <p className='title-pesqpac' style={{ margin: 0 }}>{info.titulo}</p>
                                <p className='desc-pesqpac' style={{ lineHeight: '1.6', fontSize: '14px', color: '#555', margin: '5px 0' }}>
                                    {info.desc}
                                </p>
                                <label>
                                    <input type="checkbox" />
                                </label>
                            </div>
                        ))}
                    </div>
                </div>
                <div style={{ textAlign: 'center', marginTop: '20px' }}>
                    <button
                        onClick={handleSave}
                        style={{
                            padding: '10px 20px',
                            backgroundColor: '#028275',
                            color: 'white',
                            border: 'none',
                            borderRadius: '5px',
                            cursor: 'pointer',
                            fontSize: '16px',
                        }}
                    >
                        Salvar
                    </button>
                </div>
            </div>
        </>
    );
}
