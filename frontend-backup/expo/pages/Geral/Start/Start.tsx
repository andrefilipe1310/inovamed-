import { Link } from "react-router-dom"
import "./StyleStart.css"
export default function Start(){

    return(
        <div className="container-all-start">
            <div className="header-container">
                <p>imagem aqui</p>
                <Link to='/login' className="link-login">Login</Link>
            </div>
            <div>
                imagem
            </div>
        </div>
    )
}