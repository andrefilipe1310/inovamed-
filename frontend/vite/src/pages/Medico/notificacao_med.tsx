import Navbar from "../../components/Navbar";
import criarnotificacao from './criarnotificacao_med.json'

export default function MedNotificacao(){
    interface notificacaomed {
        titulo: string;
        remetente: string;
        corpo: string;
    }
    const notificacaomed:notificacaomed[] = criarnotificacao
    return (
        <>
            <div
                className="container-card-notificacoes"
                style={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    padding: "20px",
                    maxWidth: "1200px",
                    margin: "0 auto",
                    width: "100%",
                    
                }}
            >
                {notificacaomed.map((notificacaomed, index) => (
                    <div
                        key={index}
                        className="div-dados-notificacaomed"
                        style={{
                            backgroundColor: "#fff",
                            borderRadius: "8px",
                            boxShadow: "0 4px 10px rgba(0, 0, 0, 0.1)",
                            marginBottom: "20px",
                            padding: "20px",
                            width: "100%",
                            maxWidth: "500px",
                            transition: "transform 0.3s ease, box-shadow 0.3s ease",
                          
                        }}
                    >
                        <h2
                            style={{
                                fontSize: "1.5rem",
                                color: "#028275",
                                marginBottom: "10px",
                              
                            }}
                        >
                            {notificacaomed.titulo}
                        </h2>
                        <div
                            className="corpo-notificacaomed"
                            style={{
                                color: "#555",
                                lineHeight: "1.6",
                               
                            }}
                        >
                            <p
                                style={{
                                    fontSize: "1rem",
                                    color: "#333",
                                    margin: "5px 0",
                                    
                                   
                                }}
                            >
                                {notificacaomed.remetente}
                            </p>
                            <p
                                style={{
                                    fontSize: "1rem",
                                    color: "#333",
                                    margin: "5px 0",
                                 
                                }}
                            >
                                {notificacaomed.corpo}
                            </p>
                        </div>
                    </div>
                ))}
            </div>
        </>
    );
}