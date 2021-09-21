
export function assertEquals(flag, source, target) {
    let src = null;
    let tar = null;
    switch (arguments.length) {
        case 2: {
            src = flag;
            tar = source;
            flag = '';
            break;
        }
        case 3: {
            src = source;
            tar = target;
            break;
        }
        default:
            console('junit error call');
            return false;
    }

    if (src == tar) {
        console.log(`junit ${flag}: yes`);
        return true;
    }

    console.log(`junit ${flag}: no:`);
    console.log('source=' + src);
    console.log('target=' + tar);
    return false;
}
