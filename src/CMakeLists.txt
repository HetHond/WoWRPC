set(WOWRPC_HEADER_FILES
    Color.h
    Player.h
    Vector3.h
)

set(WOWRPC_SOURCE_FILES
    Color.cpp
    main.cpp 
    Player.cpp 
    Vector3.cpp
)

add_executable(WoWRPC  ${WOWRPC_HEADER_FILES} ${WOWRPC_SOURCE_FILES})