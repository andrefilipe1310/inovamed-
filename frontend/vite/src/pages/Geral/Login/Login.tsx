
import { Link, useNavigate } from "react-router-dom"
import '../../stylePages.css'
import { useState } from "react"
import api from "../../../config/axiosConfig"
import apiUnauthorized from "../../../config/axiosUnauthorizedConfig"

export default function Login() {
    const [user, setUser] = useState({ email: "", password: "" })
    const [errorMessage, setErrorMessage] = useState<string | null>(null)
    const navigate = useNavigate()

    const handleLogin = async (event: React.FormEvent) => {
        event.preventDefault()
        setErrorMessage(null) // Limpa a mensagem de erro ao tentar fazer login
     
        apiUnauthorized.post("/auth/login", user)
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

    const [showPassword, setShowPassword] = useState(false);

    const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

    return (
        <div className="container-all-login">
            <div className="logo-container"><img src="logo_branca.svg" alt="logo" /></div>
            <div className="card-border">
                <div className="container-login">
                    <div className="container-email-senha">
                        <label htmlFor="email">EMAIL</label>
                        <input type="text" name="email" value={user.email} onChange={handleChange} />
                        <label htmlFor="senha">SENHA</label>
                        <input type={showPassword ? 'text' : 'password'} name="password" value={user.password} onChange={handleChange} />
                        <button onClick={togglePasswordVisibility} className="toggle-password"><img src="view.png" alt="" style={{width:"150%"}}/></button>
                    </div>
                    <button onClick={handleLogin} className="submit">Entrar</button>
                    {errorMessage && <p style={{color:"red"}} className="error-message">{errorMessage}</p>}
                    <p>Não é cadastrado? <Link to="/cadastro">Cadastrar</Link></p>
                </div>
            </div>
        </div>
    )
}