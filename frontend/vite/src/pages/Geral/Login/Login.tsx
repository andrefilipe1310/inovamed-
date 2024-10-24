
import { Link } from "react-router-dom"
import '../../stylePages.css'

export default function Login(){

    return(
        <div className="container-all-login">
            <div className="logo-container"><img src="../../../../public/logo_branca.svg" alt="logo" /></div>
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
                    <div style={{ display:'flex', flexDirection:'row', width:'80%', justifyContent:'space-evenly'}}>
                        <Link to='/paciente/listapesquisas'> Paciente</Link>
                        <Link to='/medico/listapesquisas'> Médico </Link>
                        <Link to='/representante/listapesquisas'> Representante</Link>
                    </div>
                </div>
            </div>
        </div>
    )
}