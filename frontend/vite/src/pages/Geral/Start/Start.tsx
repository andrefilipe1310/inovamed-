import { Link } from "react-router-dom"


export default function Start(){

    return(
        <div className="container-all-start">
            <div className="header-container">
                <img src="../../../../public/logo_branca.svg" alt="logo" className="logo-start"/>
                <Link to='/login' className="link-login">Login</Link>
            </div>
                <img src="../../../../public/TRANSPAREÊNCIA E M PRIMEIRO LUGAR.jpg" alt="" className="front-page-image" />
            <div className="rodape">
                <img src="../../../../public/logo_branca.svg" alt="logo" className="logo-start"/>
                <p>Inovamed - Rope Health</p>
                <p>Todos os direitos reservados</p>
            </div>
            
        </div>
    )
}