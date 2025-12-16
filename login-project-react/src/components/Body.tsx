
import "./Body.css"
import { Button, Card, Dropdown, Flex, Input, InputNumber, Pagination, Popconfirm, Select, Spin, } from "antd"
import { useContext, useEffect, useState } from "react"
import { Film, Genre, handleCreateFilmService, handleDeleteFilmService, handleGetAllFilmsPageService, handleGetAllFilmsService, handleGetAllGenresService, handleGetFilmByGenreService, handleGetFilmsByGenrePageService, handleUpdateFilmService } from "../service/FilmsService"
import { DeleteOutlined, DisconnectOutlined, EditOutlined, InboxOutlined, PlusCircleOutlined, } from "@ant-design/icons"
import { AppContext, openNotification } from "../App"
import Dragger from "antd/es/upload/Dragger"


export const Body = () =>{

    const [error, setError] = useState(false);
    const [loading, setLoading] = useState(true)   
    const [imgLoading, setImgLoading] = useState(true);

    const [films, setFilms] = useState<Film[]>([]);
    const [genres, setGenres] = useState<Genre[]>([]);

    const [newFilmData, setNewFilmData] = useState<Partial<Film>>({});
    const [isAddingNewFilm, setIsAddingNewFilm] = useState(false);
   
    const [editingFilmId, setEditingFilmId] = useState<number | null>(null);
    const [editedFilmData, setEditedFilmData] = useState<Partial<Film>>({});

    const [page, setPage] = useState(1);
    const [size] = useState(6);
    const [total, setTotal] = useState(0);

    const {store} = useContext(AppContext);

    const fetchFilmsPage = () => {
        setLoading(true);
        setError(false);
    
        const computedSize = store.roles?.includes("ADMIN")
        ? size - 1
        : size;

        handleGetAllFilmsPageService(page, computedSize)
        .then(response => {
            setFilms(response.content);
            setTotal(response.totalElements);
        })
        .catch(err => {
            console.error("Failed to fetch films", err);
            setError(true);
            openNotification('error', 'Failed to fetch films');
        })
        .finally(() => {
            setLoading(false);
        });
    }

    const fetchGenres = ()=>{

        handleGetAllGenresService()
        .then(response => {
            setGenres(response);
        })
        .catch(err => {
            console.error("Failed to fetch genres", err);
            openNotification('error', 'Failed to fetch genres');
        });
    }

    const deleteFilm = async (id:number)=>{

        try {
            await handleDeleteFilmService(id);
            fetchFilmsPage()
            openNotification('success', 'Film deleted successfully');
        }catch (err) {
            console.error("Failed to delete film", err);
            openNotification('error', 'Failed to delete film');
        }

    }

    const handleSaveEdit = async () => {

        try {
            await handleUpdateFilmService(editedFilmData as Film);
            setEditedFilmData({});
            setEditingFilmId(null);
            openNotification('success', 'Film updated successfully');
            fetchFilmsPage();
        }catch (err) {
            console.error("Failed to update film", err);
            openNotification('error', 'Failed to update film');
        }

    }

    const handleCreateFilm = async () => {

        try {
            await handleCreateFilmService(newFilmData as Film);
            setNewFilmData({});
            setIsAddingNewFilm(false);
            fetchFilmsPage();
        }catch (err) {
            console.error("Failed to create film", err);
            openNotification('error', 'Failed to create film');
        }
    }

    const handleFilmsByGenre = async (genreId: number) => {
        setLoading(true);
        setError(false);

        const computedSize = store.roles?.includes("ADMIN")
        ? size - 1
        : size;

        handleGetFilmsByGenrePageService(genreId, page, computedSize)
        .then(response => {
            if(response.content.length === 0){
                openNotification('info', 'No films found for the selected genre');
                setFilms([]);
            }
            setFilms(response.content);
            setTotal(response.totalElements);
        })
        .catch(err => {
            console.error("Failed to fetch films by genre", err);
            setError(true);
            openNotification('error', 'Failed to fetch films by genre');
        })
        .finally(() => {
            setLoading(false);
        });
    }
  
    useEffect(() => {

        fetchFilmsPage();

    }, [store.token, store.roles, page, size])

    useEffect(() => {
        if(store.films && store.films.length > 0){
            setFilms(store.films);
        }
    }, [store.films]);

    return (
            
        <>

            {loading && <Spin tip="loading" size="large" 
                        style={{
                            position: "absolute",
                            top: "50%",
                            left: "50%",
                            transform: "translate(-50%, -50%)"
                        }}/>
            }

            {error && 
                <div 
                    style={{
                            position: "absolute",
                            top: "50%",
                            left: "50%",
                            transform: "translate(-50%, -50%)"
                    }}
                    >
                    <DisconnectOutlined style={{fontSize:70}}/>
                    {error}
                </div>  
            }            


            {!loading && !error && (
                <>
                    <div style={{
                        padding:"16px",
                    }}>
                        <Dropdown 
                            trigger={["click"]}
                            onOpenChange={(open) => {
                                if(open){
                                    if(genres.length === 0){
                                        fetchGenres();
                                    }
                                } 
                            }}
                            menu={{
                                items: [
                                    { 
                                        key: 'grid',
                                        label: (
                                        <div
                                            style={{
                                                display:"grid",
                                                gridTemplateColumns: "repeat(3, 1fr)",
                                                gap: "8px",
                                                maxHeight: "300px",
                                                overflowY: "auto",
                                                padding:"8px",
                                            }}
                                            >
                                            {<Button
                                                key={0}
                                                type="text"
                                                size="small"
                                                onClick={() => {
                                                    setPage(1)
                                                    fetchFilmsPage()
                                                }}
                                            >
                                                All Genres
                                            </Button>}
                                            {genres.map(genre => (
                                                <Button 
                                                    key={genre.id+1}  
                                                    type="text"
                                                    size="small"
                                                    onClick={() => handleFilmsByGenre(genre.id)}
                                                >
                                                    {genre.name}
                                                </Button>
                                            ))}
                                        </div>)
                                    }          
                                ]
                            }}
                        >
                            <Button 
                                type="default"
                                onClick={(e) => {e.preventDefault();}}
                                >
                                Genres
                            </Button>    
                        </Dropdown>
                    </div>

                    <div
                        style={{
                            display:"grid",
                            gridTemplateColumns: "repeat(auto-fit, 260px)",
                            justifyContent: "start",
                            gap: "16px",
                            padding: "16px",
                        }}
                        >       
                        {films.map((film) => (
                            <Card 
                                key={film.id}
                                cover={editingFilmId === film.id ? (null) : (
                                    

                                    <div style={{ position: "relative", width: "100%", height: 375 }}>
                                        {imgLoading && (
                                            <div style={{ 
                                                position: "absolute", 
                                                inset: 0, 
                                                display: "flex", 
                                                justifyContent: "center", 
                                                alignItems: "center", 
                                                background: "#f0f0f0" 
                                                }}>
                                                <Spin tip="Loading..." />
                                            </div>
                                        )}
                                        <img
                                            draggable={false}
                                            alt={film.title}
                                            src={`http://localhost:8080/film/cover/${film.coverSrc}`}
                                            style={{ width: "100%", height: "100%", objectFit: "cover" }}
                                            onLoad={() => setImgLoading(false)}
                                        />
                                    </div>  
                                )}
                                hoverable
                                variant="borderless"
                                style={{
                                    maxWidth: 260,
                                    display: "flex",
                                    flexDirection: "column",
                                    height: "100%",
                                }}
                                actions={store.roles?.includes("ADMIN") ? [
                                    <EditOutlined
                                    key="edit"
                                    onClick={() => {
                                        setEditingFilmId(film.id);
                                        setEditedFilmData(film);
                                        fetchGenres()
                                    }}/>,
                                    <Popconfirm
                                        key="delete"
                                        title="Delete Film"
                                        description="Are you sure to delete this film?"
                                        okText="Yes"
                                        cancelText="No"
                                        placement="topRight"
                                        onConfirm={() => {deleteFilm(film.id)}}
                                    >
                                        <DeleteOutlined/>
                                    </Popconfirm>

                                ] : []}
                            >
                                {editingFilmId === film.id ? (
                                    <>

                                        <span style={{fontSize:"12px"}}>Cover:</span>

                                        <Dragger
                                            beforeUpload={(file) => {
                                                setEditedFilmData(prev => ({...prev, cover: file}))
                                                return false;
                                            }}
                                            style={{maxHeight:130}}
                                            multiple={false}
                                            accept=".jpeg,.jpg"
                                            showUploadList={{showRemoveIcon:true}}
                                            onRemove={() => setEditedFilmData(prev => ({...prev, cover:undefined}))}
                                        >

                                            <p className="ant-upload-drag-icon">
                                                <InboxOutlined />
                                            </p>
                                            <p className="ant-upload-text">Click or drag a file</p>
                                            
                                        </Dragger>

                                        <span style={{fontSize:"12px"}}>Title:</span>
                                        <Input 
                                            value={editedFilmData.title || ""}
                                            onChange={e => setEditedFilmData(prev => ({
                                                ...prev,
                                                title: e.target.value
                                            }))}
                                            placeholder="Title"
                                            style={{
                                                marginBottom:"8px"
                                            }}
                                        />
                                        <span style={{fontSize:"12px"}}>Description:</span>
                                        <Input.TextArea 
                                            value={editedFilmData.description || ""}
                                            onChange={e => setEditedFilmData(prev => ({
                                                ...prev,
                                                description: e.target.value
                                            }))}
                                            placeholder="Description"
                                            style={{
                                                marginBottom:"8px",
                                                width:"100%",
                                                height:"100px",
                                                resize:"none",
                                                overflowY:"auto"
                                            }}
                                        />

                                        <span style={{fontSize:"12px"}}>Genres</span>
                                        <Select
                                            mode="multiple"
                                            style={{ width: '100%', marginBottom:"8px" }}
                                            placeholder="Select genres"
                                            value={editedFilmData.genres?.map(g => g.id) || []}
                                            onChange={(selectedIds: number[]) =>{
                                                const selectedGenres = genres.filter(g => selectedIds.includes(g.id));
                                                setEditedFilmData(prev => ({...prev, genres: selectedGenres}))
                                            }}
                                        >
                                            {genres.map(genre => (
                                                <Select.Option 
                                                    key={genre.id}
                                                    value={genre.id}
                                                >
                                                    {genre.name}
                                                </Select.Option>
                                            ))}
                                        </Select>        

                                        <span style={{fontSize:"12px"}}>Duration (min):</span>
                                        <InputNumber
                                            value={editedFilmData.duration || 0}
                                            onChange={(value) => setEditedFilmData(prev => ({
                                                ...prev,
                                                duration: value || 0
                                            }))}
                                            placeholder={film.duration.toString()}
                                            style={{marginBottom:"8px", width:"100%"}}
                                        />
                                        <Flex gap={8}>
                                            <Button type="primary" onClick={() => handleSaveEdit()}>Save</Button>
                                            <Button onClick={() => {
                                                setEditingFilmId(null);
                                                setEditedFilmData({});
                                                }}
                                            >
                                                Cancel
                                            </Button>
                                        </Flex>
                                    </>
                                ) : (
                                <Card.Meta 
                                    title= {film.title}
                                    description={
                                        <>
                                            <p
                                                style={{
                                                    display:"-webkit-box",
                                                    WebkitLineClamp: 3,
                                                    overflow: "hidden",
                                                    textOverflow: "ellipsis",
                                                    WebkitBoxOrient: "vertical",
                                                }}
                                            >{film.description}</p>

                                            {film.genres.map(g => (
                                                <span 
                                                    key={g.id} 
                                                    style={{ 
                                                        fontSize: "12px", 
                                                        color: "#888", 
                                                        marginRight: "4px" 
                                                    }}
                                                >
                                                    {g.name}
                                                </span>
                                            ))}
                                            <br/>
                                            <span>{film.duration} min</span>
                                        </>
                                    }
                                />
                                )}
                            </Card>
                        ))}
                        {store.roles?.includes("ADMIN") && (
                            <Card 
                                key="new-film"
                                style={{
                                    position: "relative",
                                    width: 260,
                                    height: "100%",
                                    display: "flex",
                                    flexDirection: "column",
                                    padding: "16px",
                                    }}
                            >
                                {isAddingNewFilm ? (
                                    <div style={{width:"100%"}}>   
                                        
                                        <span style={{fontSize:"16px", fontWeight:"bold"}}>New Film</span>
                                        <br/>
                                        <br/>
                                        
                                        <span style={{fontSize:"12px"}}>Cover:</span>

                                        <Dragger
                                            beforeUpload={(file) => {
                                                setNewFilmData(prev => ({...prev, cover: file}))
                                                return false;
                                            }}
                                            multiple={false}
                                            accept=".jpeg,.jpg"
                                            showUploadList={{showRemoveIcon:true}}
                                            onRemove={() => setNewFilmData(prev => ({...prev, cover:undefined}))}
                                        >
                                            
                                            <p className="ant-upload-drag-icon">
                                            <InboxOutlined />
                                            </p>
                                            <p className="ant-upload-text">Click or drag a file</p>
                                        
                                        </Dragger>


                                        <span style={{fontSize:"12px"}}>Title:</span>
                                        <Input 
                                            value={newFilmData.title || ""}
                                            onChange={e => setNewFilmData(prev => ({...prev, title: e.target.value}))}
                                            placeholder="Title"
                                            style={{
                                                marginBottom:"8px"
                                            }}
                                        />
                                        <span style={{fontSize:"12px"}}>Description:</span>
                                        <Input.TextArea 
                                            value={newFilmData.description || ""}
                                            onChange={e => setNewFilmData(prev => ({...prev, description: e.target.value}))}
                                            placeholder="Description"
                                            style={{
                                                marginBottom:"8px",
                                                width:"100%",
                                                height:"150px",
                                                resize:"none",
                                                overflowY:"auto"
                                            }}
                                        />
                                        <span style={{fontSize:"12px"}}>Duration (min):</span>
                                        <InputNumber
                                            value={newFilmData.duration || 0}
                                            onChange={(value) => setNewFilmData(prev => ({...prev, duration: value || 0}))}
                                            placeholder="Duration"
                                            style={{marginBottom:"8px", width:"100%"}}
                                        />

                                        <span style={{fontSize:"12px"}}>Genres</span>
                                        <Select
                                            mode="multiple"
                                            style={{ width: '100%', marginBottom:"8px" }}
                                            placeholder="Select genres"
                                            value={newFilmData.genres?.map(g => g.id) || []}
                                            onChange={(selectedIds: number[]) =>{
                                                const selectedGenres = genres.filter(g => selectedIds.includes(g.id));
                                                setNewFilmData(prev => ({...prev, genres: selectedGenres}))
                                            }}
                                        >
                                            {genres.map(genre => (
                                                <Select.Option 
                                                    key={genre.id}
                                                    value={genre.id}
                                                >
                                                    {genre.name}
                                                </Select.Option>
                                            ))}
                                        </Select>
                                        <Flex gap={8}>
                                            <Button type="primary" onClick={() => handleCreateFilm()}>Create</Button>
                                            <Button onClick={() => setIsAddingNewFilm(false)}>Cancel</Button>
                                        </Flex>
                                    </div>
                                ) : (
                                    <Button 
                                        variant="link" 
                                        color="default" 
                                        onClick={() => {
                                            setIsAddingNewFilm(true)
                                            fetchGenres()
                                        }}
                                        style={{position:"absolute",top:"50%",left:"50%",transform:"translate(-50%,-50%)"}}
                                    >
                                        <PlusCircleOutlined style={{fontSize:"30px"}} />
                                    </Button>
                
                                )}
                            </Card>
                            )
                        }    
                    </div>
                    <div
                        style={{
                            margin: "20px 0 50px 0",
                        }}
                    >
                        <br></br>
                        <Pagination
                            simple={{ readOnly: true }}
                            current={page}
                            pageSize={size}
                            total={total}
                            onChange={(newPage) => setPage(newPage)}
                            showSizeChanger={false}
                        />
                    </div>
                </>
            )}


        
        </>
    )

}