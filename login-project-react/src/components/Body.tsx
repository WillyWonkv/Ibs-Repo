
import "./Body.css"
import { Alert, Card, Flex, Spin } from "antd"
import { useContext, useEffect, useState } from "react"
import { Film, handleDeleteFilm, handleGetAllFilms } from "../service/FilmsService"
import { DeleteOutlined, EditOutlined, EllipsisOutlined } from "@ant-design/icons"
import { jwtDecode } from "jwt-decode"
import { TokenPayload } from "../service/UsersService"
import { AppStoreContext } from "../App"


export const Body = () =>{

    const [loading, setLoading] = useState(true)   
    const [films, setFilms] = useState<Film[]>([]);
    const [roles, setRoles] = useState<string[]>([]);
    const [err, setErr] = useState<string | null>(null);

    const {setStore,store} = useContext(AppStoreContext);

    const fetchFilms = async ()=>{
        try{
            const films = await handleGetAllFilms();
            setFilms(films);

        }catch(error){
            console.error(error)
            setErr("Error loading films, check the connection");
        }finally{
            setLoading(false);
        }
    };

    const setroles = () => {
        const token = localStorage.getItem("token");
        if(token){
            const decoded = jwtDecode<TokenPayload>(token);
            setRoles(decoded.roles || []);
        }else{
            setRoles([]);
        }
    }

    useEffect(() => {
        fetchFilms();
    }, [])

    useEffect(()=>{
        setroles();
    },[store]);

    const deleteFilm = async (id:number)=>{
        const ok = await handleDeleteFilm(id);

        if(ok){
            setFilms(prev => prev.filter(f => f.id !== id));
        }else{
            setErr("Error deleting film")
        }

    }

    return (
        <div
            style={{
                display:"grid",
                gridTemplateColumns: "repeat(auto-fit, minmax(240px, 1fr))",
                gap: "16px",
                padding: "16px",
            }}
            >
            {loading ? (
                <Spin tip="loading" size="large"/>
            ) : (
                films.map((film) => (

                    <Card 
                        key={film.id}
                        cover={<img draggable={false} alt="Foto" src={film.coverSrc} style={{height:"375px"}}/>}
                        hoverable
                        variant="borderless"
                        style={{width:"100%"}}
                        actions={roles.includes("ADMIN") ? [
                            <EditOutlined key="edit"/>,
                            <DeleteOutlined
                                key="delete" 
                                onClick={() => deleteFilm(film.id)}/>,
                            <EllipsisOutlined key="ellipsis"/>,
                        ] : []}
                    >
                        <Card.Meta 
                            title={film.title}
                            description={
                                <>
                                    <p>{film.description}</p>
                                    <p>{film.duration} Minutes</p>
                                </>
                            }
                        />
                    </Card>
            )))}
        </div>
    )

}