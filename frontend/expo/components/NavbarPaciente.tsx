import { Link } from "react-router-dom"
import './navbar.css'

export default function NavbarPaciente(){

    return(
        <div className="container-navbar">
            <div>
                <p>foto</p>
                <h1>nome</h1>
            </div>
            <div>
                <li><Link to='/paciente/listapesquisas'>Suas Pesquisas</Link></li>
                <li><Link to='/paciente/preferencias'>Preferências</Link></li>
                <li><Link to='/paciente/notificacoes'>Notificações</Link></li>
            </div>
        </div>
    )
}