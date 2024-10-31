import Navbar from "../../components/Navbar";
import notificacaopaciente from "../../components/notificacoes-pac";

export default function PacCriarNotificacao() {
    return (
        <>
            <Navbar />
            <div className='container-page-medico'>
                <h1 className="h1notificacaopac" style={{ color: '#028275' }}>NOTIFICAÇÕES</h1>

                <div className="notificacaopac" style={{ display: 'flex', alignItems: 'center', marginTop: '20px' }}>
                    <h3 style={{ marginRight: '10px' }}>Pesquisa Clínica:</h3>
                    <p>Essa pesquisa visa mostrar o resultado do tratamento X para pacientes com Alzheimer.</p>
                </div>

                <div className="container-card-notificacoespac">
                    {notificacaopaciente.map((notificacaopac) => (
                        <div 
                            className="div-dados-notifcacaopac" 
                            key={notificacaopac.titulo} 
                            style={{
                                backgroundColor: 'white',
                                borderRadius: '8px',
                                padding: '16px',
                                marginBottom: '16px',
                                boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
                                border: 'none', 
                            }}
                        >
                            <h2>{notificacaopac.titulo}</h2>
                            <div className="corpo-notificacaomed">
                                <p style={{ fontWeight: 'bold' }}>{notificacaopac.remetente}</p>
                                <p>{notificacaopac.corpo}</p>

                               
                                {notificacaopac.documentUrl && (
                                    <div className="flex items-center space-x-2">
                                        <a 
                                            href={notificacaopac.documentUrl}
                                            className="text-sm text-blue-500 underline"
                                            target="_blank" 
                                            rel="noopener noreferrer" 
                                        >
                                            Link
                                        </a>
                                    </div>
                                )}

                                <div style={{ marginTop: '10px' }}>
                                    <a 
                                        href="https://link-para-documento-adicional.com"
                                        className="text-sm text-blue-500 underline"
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        Documento 
                                    </a>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
}
