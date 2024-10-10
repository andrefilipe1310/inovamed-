import { Link, useLocation } from "react-router-dom"
import './navbar.css'

export default function Navbar(){

    const Identifier = useLocation()
    console.log(Identifier.pathname)

    if (Identifier.pathname.startsWith('/representante')){
    return(
        <div className="container-navbar">
            <div className="container-perfil">
                <img src="../../public/user-icon.svg" alt="user-icon" className="user-icon" />
                <h1>Dr. Ricardo</h1>
            </div>
            <div className="container-links">
                <Link to='/representante/listapesquisas' className='nav-item'>Suas Pesquisas</Link>
                <Link to='/representante/notificacoes' className='nav-item'>Notificações</Link>
                <Link to='/' className='nav-item'>Sair</Link>
            </div>
        </div>
    )
}else if (Identifier.pathname.startsWith('/paciente')) {
    return(
        <div className="container-navbar">
            <div className="container-perfil">
                <img src="../../public/user-icon.svg" alt="user-icon" className="user-icon" />
                <h1>Geraldo</h1>
            </div>
            <div className="container-links">
                <Link to='/paciente/listapesquisas' className='nav-item'>Suas Pesquisas</Link>
                <Link to='/paciente/notificacoes' className='nav-item'>Notificações</Link>
                <Link to='/paciente/preferencias' className='nav-item'>Preferências</Link>
                <Link to='/' className='nav-item'>Sair</Link>
            </div>
        </div>
    )
}else if (Identifier.pathname.startsWith('/medico')) {
    return(
        <div className="container-navbar">
            <div className="container-perfil">
                <img src="../../public/user-icon.svg" alt="user-icon" className="user-icon" />
                <h1>Dr. Angela</h1>
            </div>
            <div className="container-links">
                <Link to='/medico/listapesquisas' className='nav-item'>Pesquisas Disponíveis</Link>
                <Link to='/medico/notificacoes' className='nav-item'>Notificações</Link>
                <Link to='/' className='nav-item'>Sair</Link>
            </div>
        </div>
    )
}

}