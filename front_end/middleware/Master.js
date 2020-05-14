export default function ({ store, redirect }) {
    if (!store.state.auth) {
        return redirect('/login')
    }
    if (store.state.auth.role ==="ADMIN"){
        return redirect('/admin/addUser')
    }
    else if (store.state.auth.role ==="STUDENT"){
        return redirect('/safePage')
    }
    else if (store.state.auth.role ==="MANAGER"){
        return redirect('/safePage')
    }
    else if (store.state.auth.role ==="TEACHER"){
        return redirect('/safePage')
    }
}
