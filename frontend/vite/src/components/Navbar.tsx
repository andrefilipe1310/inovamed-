import { Link, useLocation } from "react-router-dom"
import './navbar.css'

export default function Navbar(){

    let Identifier = useLocation()
    console.log(Identifier.pathname)

    if (Identifier.pathname.startsWith('/representante')){
    return(
        <div className="container-navbar">
            <div>
                <p>foto</p>
                <h1>nome</h1>
            </div>
            <div>
                <li><Link to='/representante/listapesquisas'>Suas Pesquisas</Link></li>
                <li><Link to='/representante/notificacoes'>Notificações</Link></li>
                <li><Link to='/'>Sair</Link></li>
            </div>
        </div>
    )
}else if (Identifier.pathname.startsWith('/paciente')) {
    return(
        <div className="container-navbar">
            <div>
                <p>foto</p>
                <h1>nome</h1>
            </div>
            <div>
                <li><Link to='/paciente/listapesquisas'>Suas Pesquisas</Link></li>
                <li><Link to='/paciente/notificacoes'>Notificações</Link></li>
                <li><Link to='/paciente/preferencias'>Preferências</Link></li>
                <li><Link to='/'>Sair</Link></li>
            </div>
        </div>
    )
}else if (Identifier.pathname.startsWith('/medico')) {
    return(
        <div className="container-navbar">
            <div>
                <p>foto</p>
                <h1>nome</h1>
            </div>
            <div>
                <li><Link to='/medico/listapesquisas'>Pesquisas Disponíveis</Link></li>
                <li><Link to='/medico/notificacoes'>Notificações</Link></li>
                <li><Link to='/'>Sair</Link></li>
            </div>
        </div>
    )
}

}