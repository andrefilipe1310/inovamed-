
import { Link } from "react-router-dom"
import './Login.css'
export default function Login(){

    return(
        <div className="container-all-login">
            <div className="logo-container">logo aqui</div>
            <div className="border-container-login">
                <div className="container-login">
                    <div className="container-email-senha">
                        <label htmlFor="email">EMAIL</label>
                        <input type="text" name="email"/>
                        <label htmlFor="senha">SENHA</label>
                        <input type="text" name="senha"/>      
                    </div>
                    <button>Entrar</button>
                    <p>Esqueceu a senha? <a href="">Recuperar senha</a></p>
                    <p>Não é cadastrado? <a href="">Cadastrar</a></p>
                </div>
            </div>
        </div>
    )
}