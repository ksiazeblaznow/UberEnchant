## Fork of UberEnchant by coltonj96
[UberEnchant by coltonj96](https://github.com/coltonj96/UberEnchant)

### Why?

Because of tasks and tags leaking - in the original repo tasks are stored in `ArrayList` and removed on specific conditions - those were failing in some edge cases.
Another leak was happening inside of `UberMeta` tags creation - resulting in easily 17k+ entries.
I stumbled upon those that led us to ~10TPS performance, and the server was running on Raspberry Pi 5.
At that moment reloading server caused a relieve for ~20 mins, then it raises gradually easily up to 170ms and more.

### Change
- Replaced the underlying `ArrayList` tracking system with a `HashMap` (`PlayerUUID_SLOT`). Tasks are now uniquely mapped per equipment slot, automatically overwriting and discarding old tasks on insertion.
- Introduced checks if `UberMeta` tag already exists. Reduced array size to ~12 from ever-growing number (17k+)
