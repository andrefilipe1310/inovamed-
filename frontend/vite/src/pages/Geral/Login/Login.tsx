
import { Link, useNavigate } from "react-router-dom"
import '../../stylePages.css'
import { useState } from "react"
import api from "../../../config/axiosConfig"

export default function Login() {
    const [user, setUser] = useState({ email: "", password: "" })
    const [errorMessage, setErrorMessage] = useState<string | null>(null)
    const navigate = useNavigate()

    const handleLogin = async (event: React.FormEvent) => {
        event.preventDefault()
        setErrorMessage(null) // Limpa a mensagem de erro ao tentar fazer login

        api.post("/auth/login", user)
            .then(response => {
                
                if (response.data.token && response.data.role) {
                    localStorage.setItem("token",response.data.token)
                    switch (response.data.role.toUpperCase()) {
                        case "PATIENT":
                            navigate("/paciente/listapesquisas")
                            break;
                        case "STUDY_REPRESENTATIVE":
                            navigate("/representante/listapesquisas") 
                            break;
                        case "DOCTOR":
                            navigate("/medico/listapesquisas") 
                            break;
                    
                        default:
                            break;
                    }
                    
                }
                
            })
            .catch(error => {
                 console.error("Erro no login:", error);
                if (!error.response) {
                    setErrorMessage("O servidor está offline. Tente novamente mais tarde.");
                } else {
                    setErrorMessage("Erro ao fazer login. Verifique suas credenciais.");
                }
            });

    }
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target
        setUser((prevUser) => ({ ...prevUser, [name]: value }))

    }

    return (
        <div className="container-all-login">
            <div className="logo-container"><img src="../../../../public/logo_branca.svg" alt="logo" /></div>
            <div className="border-container-login">
                <div className="container-login">
                    <div className="container-email-senha">
                        <label htmlFor="email">EMAIL</label>
                        <input type="text" name="email" value={user.email} onChange={handleChange} />
                        <label htmlFor="senha">SENHA</label>
                        <input type="text" name="password" value={user.password} onChange={handleChange} />
                    </div>
                    <button onClick={handleLogin}>Entrar</button>
                    {errorMessage && <p style={{color:"red"}} className="error-message">{errorMessage}</p>}
                    <p>Esqueceu a senha? <a href="">Recuperar senha</a></p>
                    <p>Não é cadastrado? <a href="">Cadastrar</a></p>
                    <div style={{ display: 'flex', flexDirection: 'row', width: '80%', justifyContent: 'space-evenly' }}>
                        <Link to='/paciente/listapesquisas'> Paciente</Link>
                        <Link to='/medico/listapesquisas'> Médico </Link>
                        <Link to='/representante/listapesquisas'> Representante</Link>
                    </div>
                </div>
            </div>
        </div>
    )
}